package muface.arch.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class ArqCustomAuthenticationFilter extends OncePerRequestFilter {

    @Value("${arch.white-list}")
    private String whiteUrisTokenized;

    @Value("${arch.role-mapping-default}")
    private String roleMapping;

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

        // Buscamos el rol básico que permite la autenticación mínima de acceso:: primera barrera
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().isEmpty()
                    || !authentication.getAuthorities().contains(new SimpleGrantedAuthority(roleMapping))) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "no autenticado con token válido (cambiar por idioma)");
                return;
            }
        } else {
            // Si no está autenticado, puedes denegar la solicitud
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autenticado con token válido (cambiar por idioma)");
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

