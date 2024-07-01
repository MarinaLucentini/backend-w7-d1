package marinalucentini.backend_w7_d1.employee.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import marinalucentini.backend_w7_d1.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTool jwtTools;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) throw new UnauthorizedException("Per favore inserisci correttamente il token nell'header");


        String accessToken = authHeader.substring(7);

        jwtTools.verifyToken(accessToken);

        filterChain.doFilter(request, response);


    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
