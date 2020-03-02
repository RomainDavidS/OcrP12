package p12.ocr.web.technical.rolechecker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Permet de controler la correspondance d'un role avec le role utilisateur connecté
 */
@Component
public class RoleChecker implements IRoleChecker {

    @Autowired(required = false)
    private GrantedAuthorityDefaults grantedAuthorityDefaults;

    @Override
    public boolean hasRole(String role) {
        String rolePrefix = grantedAuthorityDefaults != null ? grantedAuthorityDefaults.getRolePrefix() : "ROLE_";

        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (auth.getAuthority().equals(rolePrefix + role))
                return true;
        }

        return false;
    }
}
