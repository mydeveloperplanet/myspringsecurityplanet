package com.mydeveloperplanet.myspringsecurityplanet;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;

public class KeycloakResourceAccessRolesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private static final String CLAIM_RESOURCE_ACCESS = "resource_access";
    private static final String CLAIM_ROLES = "roles";
    private static final String PREFIX = "ROLE_"; // Spring Security expects this prefix

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> resourceAccess = jwt.getClaim(CLAIM_RESOURCE_ACCESS);
        if (resourceAccess != null) {
            resourceAccess.forEach((resource, value) -> {
                Map<String, Object> resourceMap = (Map<String, Object>) value;
                Collection<String> roles = (Collection<String>) resourceMap.get(CLAIM_ROLES);
                if (roles != null) {
                    roles.forEach(role -> authorities.add(
                        new SimpleGrantedAuthority(PREFIX + role)
                    ));
                }
            });
        }
        return authorities;
    }
}
