package org.zil.gateway.filter;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private final JWTUtil jwtUtil;
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            exchange.getRequest().getURI();
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                throw new RuntimeException("Authorization header is required");

            String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
            String token = authHeader.substring(7);

            //Invoke jwt
            Claims claims = jwtUtil.extractClaimsFromToken(token);

            final Date expiration = claims.getExpiration();
            if(expiration.before(new Date()))
                throw new RuntimeException("Error! Your session has expired");

            exchange.getRequest().mutate().header("x-auth-user-id", claims.getSubject());
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }

}
