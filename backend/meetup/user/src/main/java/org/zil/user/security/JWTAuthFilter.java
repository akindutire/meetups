package org.zil.user.security;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        userEmail = jwtSvc.extractUserEmailFromToken(jwtToken);

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
