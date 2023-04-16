package org.zil.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zil.user.dto.externals.XValidUserRes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserCtrlV1 {

    private final UserSvc userSvc;

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody UserReq req) {

        log.info("User registration {}", req);
        boolean r = userSvc.register(req);

        Map<String, Object> res = new HashMap<>();

        if (r){
            res.put("message", req.getEmail() + " has been registered");
            res.put("status", HttpStatus.OK.value());
            res.put("code", HttpStatus.OK);
            res.put("data", req);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("isvalid/{userId}")
    public ResponseEntity<XValidUserRes> isValid(@Valid @PathVariable(name="userId") Integer userId) {
        XValidUserRes res = new XValidUserRes(userSvc.isValid(userId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
