package com.msbills.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    public Collection<GrantedAuthority> convert(Jwt source) {

        Collection<GrantedAuthority> authorities = new ArrayList<>();


        Map<String, Object> realmRolesAccess = source.getClaim("realm_access");
        List<String> groups = source.getClaim("group-token");


        if (realmRolesAccess != null && !realmRolesAccess.isEmpty()) {
            authorities.addAll(extractRoles(realmRolesAccess));
        }

        if (groups != null && !groups.isEmpty()) {
            for (String group : groups) {
                authorities.add(new SimpleGrantedAuthority(group));
            }
        }

        return authorities;
    }

    private static Collection<GrantedAuthority> extractRoles(Map<String, Object> realmRolesAccess) {
        return ((List<String>) realmRolesAccess.get("roles"))
                .stream().map(roleMap -> "ROLE_" + roleMap)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}