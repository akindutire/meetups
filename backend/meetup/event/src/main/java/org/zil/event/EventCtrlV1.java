package org.zil.event;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zil.event.request.CreateEventReq;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/events")
@AllArgsConstructor
public class EventCtrlV1 {

    private final EventSvc eventSvc;

    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody CreateEventReq req) {

        boolean r = eventSvc.create(req);

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
