package com.app.Epharmacy.Config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return "/admin/commandes";
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                return "/medicaments";
            }
        }
        throw new IllegalStateException("Role not found for user");
    }

    /*@Autowired
    private ClientInfoRepository clientInfoRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();
        ClientInfo user = clientInfoRepository.findByEmail(email);

        if (user != null && "ADMIN".equals(user.getRole())) {
            response.sendRedirect("/admin/commandes");
        } else {
            response.sendRedirect("/medicaments");
        }
    }*/

}

