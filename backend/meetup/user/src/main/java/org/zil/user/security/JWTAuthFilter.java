package org.zil.user.security;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.*;

@Component
@AllArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter  {

    private final JWTSvc jwtSvc;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.trim().substring(7);
        try {
            userEmail = jwtSvc.extractUserEmailFromToken(jwtToken);
        } catch (ExpiredJwtException e) {
            filterChain.doFilter(request, response);
            return;
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //Not Authenticated yet and token is valid and needs to be authenticated
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (!jwtSvc.isTokenExpired(jwtToken)) {
                //update security context
                Map<String,Object> claims = jwtSvc.extractClaimsFromToken(jwtToken);
                Collection<SimpleGrantedAuthority> grantedAuth = ((List<String>) claims.get("granted_authorities"))
                        .stream().map(SimpleGrantedAuthority::new)
                        .toList();

                if (grantedAuth.size() == 0)
                    throw new InsufficientAuthenticationException("Your request is unauthorized and can't be granted a response");

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, grantedAuth);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } else {
                throw new InsufficientAuthenticationException("Your request is unauthenticated and can't be granted a response");
            }
        }

        filterChain.doFilter(request, response);

    }
}
