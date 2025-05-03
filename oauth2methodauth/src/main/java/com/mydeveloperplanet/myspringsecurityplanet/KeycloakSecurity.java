package com.mydeveloperplanet.myspringsecurityplanet;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

@Component("keycloakSecurity")
public class KeycloakSecurity {
    public String preferredUsername(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof DefaultOidcUser oidcUser) {
            return oidcUser.getClaimAsString("preferred_username");
        }
        // handle other principal types if needed
        return null;
    }
}
