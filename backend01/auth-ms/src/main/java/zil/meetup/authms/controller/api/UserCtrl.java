package zil.meetup.authms.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zil.meetup.authms.dto.UserRegistrationReq;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserCtrl {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserRegistrationReq userRegistrationReq) {

    }


}
