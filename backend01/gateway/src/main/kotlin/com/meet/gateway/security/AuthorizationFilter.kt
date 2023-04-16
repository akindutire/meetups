package com.meet.gateway.security

import io.jsonwebtoken.Claims
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.netty.http.server.HttpServerRequest
import org.springframework.beans.factory.annotation.Autowired


@Component
class AuthorizationFilter() : AbstractGatewayFilterFactory<Any>() {

    @Autowired
    lateinit var jwtProvider: JwtProvider


    override fun apply(config: Any): GatewayFilter? {

        return GatewayFilter {
            exchange: ServerWebExchange, chain: GatewayFilterChain ->
            try {

                val req: HttpServerRequest = exchange.request as HttpServerRequest
                println(req.requestHeaders().toString())
                val headers = req.requestHeaders()
                if( headers.contains(HttpHeaders.AUTHORIZATION) ) {
                    val authHeaderValue:String = req.requestHeaders().get(HttpHeaders.AUTHORIZATION)
                    val jwt:String = authHeaderValue.replace("Bearer ", "")
                    if (jwtProvider.isJWTValid(jwt)){
                        val claims: MutableMap<String, Any?> = jwtProvider.getAllClaimsFromToken(jwt)?: mapOf<String, Any?>("detail_user_token" to "", "granted_authorities" to "") as MutableMap<String, Any?>
                        exchange.request.mutate()
                            .header("detail_user_token", claims["detail_user_token"].toString())
                            .header("granted_authorities", claims["granted_authorities"].toString())
                            .build()
                    }else{
                        throw SecurityException("Request token is invalid")
                    }
                }else{
                    throw SecurityException("Request auth header is missing")
                }
                chain.filter(exchange)
            } catch (e: Exception) {
                e.message?.let { this.onError(exchange, it, HttpStatus.UNAUTHORIZED) }
            }
        };

    }

    private fun onError(exchange: ServerWebExchange, err: String, httpStatus: HttpStatus): Mono<Void?>? {
        val response: org.springframework.http.server.reactive.ServerHttpResponse = exchange.response
        response.statusCode = httpStatus
        return response.setComplete()
    }
}
