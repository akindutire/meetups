package org.zil.user.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zil.user.UserSvc;
import org.zil.user.dto.externals.XValidUserRes;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/user/")
@AllArgsConstructor
public class UserCtrlV1 {

    private final UserSvc userSvc;

    @GetMapping("isvalid/{userId}")
    public ResponseEntity<XValidUserRes> isValid(@Valid @PathVariable(name="userId") Integer userId) {
        XValidUserRes res = new XValidUserRes(userSvc.isValid(userId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
