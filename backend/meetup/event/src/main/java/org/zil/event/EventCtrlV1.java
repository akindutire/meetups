package org.zil.event;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zil.event.request.CreateEventReq;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/event")
@AllArgsConstructor
public class EventCtrlV1 {

    private final EventSvc eventSvc;

    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody CreateEventReq req, @RequestHeader(name = "x-auth-user-id") String authEmail) {

        boolean r = eventSvc.create(req, authEmail);

        Map<String, Object> res = new HashMap<>();

        if (r){
            res.put("message", req.getTitle() + " has been created");
            res.put("status", HttpStatus.OK.value());
            res.put("code", HttpStatus.OK);
            res.put("data", req);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
