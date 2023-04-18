package org.zil.event;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.zil.event.dto.user.XValidUserRes;
import org.zil.event.request.CreateEventReq;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class EventSvc {

    private final EventRepo eventRepo;
    private final RestTemplate mrest;
    private final RabbitTemplate rabbitTemplate;
    private final EventConfig config;

    public boolean create(CreateEventReq req) {

        if(!req.getStartAt().isAfter(LocalDateTime.now().minusSeconds(1L)))
            throw new IllegalStateException("Event start date cannot be behind the current time");

        if (eventRepo.findByTitleAndOwnership(req.getTitle(), req.getOwnedBy()).isPresent())
            throw new DuplicateKeyException("Event already in use");

        //check if ownership has another event during same duration
        Date start = Date.from(req.getStartAt().atZone(ZoneId.of("Africa/Lagos")).toInstant());
        if (eventRepo.findByOwnershipAndDateInBetweenDates(req.getOwnedBy(), start).size() > 0)
            throw new IllegalStateException("One of your event is existing within time window specified in start date");

        //if user exist
        XValidUserRes res = mrest.getForObject("http://USER/api/v1/user/isvalid/{userId}", XValidUserRes.class, req.getOwnedBy());
        if (res == null)
            throw new RuntimeException("Couldn't verify owning user genuinely");

        if (!res.exist())
            throw new IllegalStateException("The owning user doesn't exist in the system");

        res = null;

        Date end = Date.from(req.getStartAt().plusMinutes(req.getDuration()).atZone(ZoneId.of("Africa/Lagos")).toInstant());
//        Event ev = Event.builder()
//                .ownership(req.getOwnedBy())
//                .title(req.getTitle())
//                .duration(req.getDuration())
//                .startAt(start)
//                .endAt(end)
//                .createdAt(new Date())
//                .build();
//
//        ev.setModifiedAt();
//
//        eventRepo.saveAndFlush(ev);

        Map<String, String> objectMap = new HashMap<>();
        objectMap.put("message", "Now you see me");
        objectMap.put("type", "EMAIL");
        objectMap.put("target", "akindutire33@gmail.com");

        rabbitTemplate.convertAndSend(config.NOTIF_QUEUE, objectMap);
        return true;
    }
}
