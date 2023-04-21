package org.zil.gateway.filter;

import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Objects;

@Component
//@RequiredArgsConstructor
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private final JWTUtil jwtUtil;

    public AuthorizationFilter(JWTUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header is required");

            String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
            String token = authHeader.substring(7);

            //Invoke jwt
            try {
                Claims claims = jwtUtil.extractClaimsFromToken(token);
                final Date expiration = claims.getExpiration();
                if(expiration.before(new Date()))
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Session has expired");

                exchange.getRequest().mutate().header("x-auth-user-id", claims.getSubject()).header("x-token", token).build();
                return chain.filter(exchange);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
            }

        });
    }

    public static class Config{

    }
}
