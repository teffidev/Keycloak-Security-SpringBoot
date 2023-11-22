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

    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> realmRolesAccess = source.getClaim("realm_access");

        if (realmRolesAccess != null && !realmRolesAccess.isEmpty()) {
            authorities.addAll(extractRoles(realmRolesAccess));
        }
        return authorities;
    }

    private static Collection<GrantedAuthority> extractRoles(Map<String, Object> realmRolesAccess) {
        return ((List<String>) realmRolesAccess.get("roles")) //ROLE_app_admin
                .stream().map(roleMap -> "ROLE_" + roleMap)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}