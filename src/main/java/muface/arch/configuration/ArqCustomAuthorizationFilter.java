package muface.arch.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class ArqCustomAuthorizationFilter extends OncePerRequestFilter {

    @Value("${arch.application-id}")
    private String applicationId;

    @Value("${arch.white-list}")
    private String whiteUrisTokenized;

    private String[] whiteUris;

    protected String[] getWhiteUriList() {
        if (this.whiteUris == null) {
            this.whiteUris = this.whiteUrisTokenized.trim().split("\\s*, \\s*");
        }
        return this.whiteUris;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        for (String whiteUri : getWhiteUriList()) {
            if (requestURI.startsWith(whiteUri)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        // Obtén la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Lógica de autorización personalizada
        String username = authentication.getName();
        logger.info("Invoking para obtener ROLES del usuario  <" + username + "> en la aplicación <" + applicationId + ">");
        //invocación a SICA con las credenciales que en el token ha enviado el frontend
        boolean hasRolValid = true; //ws.invokeSICA(username, applicationId);
        if (!hasRolValid) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "no autorizado (cambiar por idioma)");
            return;
        }

        // Continúa con el siguiente filtro
        filterChain.doFilter(request, response);
    }


    private static Jwt getJwt(JwtAuthenticationToken authentication) {
        JwtAuthenticationToken jwtAuthentication = authentication;
        Jwt credentials = (Jwt) jwtAuthentication.getCredentials();
        return credentials;
    }

}

