package com.zil.meeting.user.controller.api;

import com.zil.meeting.user.request.UserRegistrationReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users/")
public class User {

    @GetMapping("register")
    public ResponseEntity<Map<?,?>> registerUser(@RequestBody UserRegistrationReq userRegistrationReq) {

        Map<String, Object> res = new HashMap<>();
        res.put("message", "User exists");
        res.put("status", HttpStatus.OK.value());
        res.put("code", HttpStatus.OK);
        res.put("data", new Object());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
