package com.msbills.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final static String CLIENT_NAME = "parcial-client";

    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> realmRolesAccess = source.getClaim("realm_access");

        if (realmRolesAccess != null && !realmRolesAccess.isEmpty()) {
            authorities.addAll(extractRoles(realmRolesAccess));
        }

        Map<String, Object> resourceAccess = source.getClaim("resource_access");

        Map<String, Object> resource;

        Collection<String> resourceRoles;

        if (resourceAccess != null && (resource = (Map<String, Object>) resourceAccess.get(CLIENT_NAME)) != null
                && (resourceRoles = (Collection<String>) resource.get("roles")) != null) {
            authorities.addAll(extractClientRoles(resourceRoles));
        }

        return authorities;
    }

    private static Collection<GrantedAuthority> extractRoles(Map<String, Object> realmRolesAccess) {
        return ((List<String>) realmRolesAccess.get("roles"))
                .stream().map(roleMap -> "ROLE_" + roleMap)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private static Collection<GrantedAuthority> extractClientRoles(Collection<String> resourceRoles) {
        return resourceRoles
                .stream()
                .map(roleMap -> new SimpleGrantedAuthority("ROLE_" + roleMap))
                .collect(Collectors.toList());
    }
}
