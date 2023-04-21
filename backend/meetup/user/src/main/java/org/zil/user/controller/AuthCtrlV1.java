package org.zil.user.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zil.user.UserSvc;
import org.zil.user.dto.externals.XValidUserRes;
import org.zil.user.dto.internals.TokenValidationReq;
import org.zil.user.dto.internals.UserLoginReq;
import org.zil.user.dto.internals.UserRegReq;
import org.zil.user.security.JWTSvc;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/")
@AllArgsConstructor
public class AuthCtrlV1 {

    private final UserSvc userSvc;
    private final JWTSvc jwtSvc;

    @RateLimiter(name = "auth-login", fallbackMethod = "")
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginReq req) {

        log.info("User login {}", req);

        Map<String, Object> resp = userSvc.authenticate(req);
        Map<String, Object> res = new HashMap<>();

        res.put("message", "Login successful");
        res.put("status", HttpStatus.OK.value());
        res.put("code", HttpStatus.OK);
        res.put("data", resp);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RateLimiter(name = "auth-register", fallbackMethod = "")
    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegReq req) {

        log.info("User registration {}", req);
        Map<String, Object> resp =  userSvc.register(req);

        Map<String, Object> res = new HashMap<>();

        res.put("message", req.getEmail() + " has been registered");
        res.put("status", HttpStatus.OK.value());
        res.put("code", HttpStatus.OK);
        res.put("data", resp);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("validate")
    public ResponseEntity<?> validate(@RequestBody TokenValidationReq req) {


        Boolean resp =  jwtSvc.isTokenExpired(req.token());

        Map<String, Object> res = new HashMap<>();

        Map<String, Object> respp = new HashMap<>();
        respp.put("expired", resp);

        res.put("message", "Tok val");
        res.put("status", HttpStatus.OK.value());
        res.put("code", HttpStatus.OK);
        res.put("data", respp);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
