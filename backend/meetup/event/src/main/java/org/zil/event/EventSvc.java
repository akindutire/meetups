package org.zil.event;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.*;
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

    @Retry(name = "create-event", fallbackMethod = "createEventFallback")
    public boolean create(CreateEventReq req, String currentUserEmail, String authToken) {

        if (currentUserEmail == null)
            throw new IllegalStateException("Can't handle this request, unable to resolve authorized user for this request");

        if(!req.getStartAt().isAfter(LocalDateTime.now().minusSeconds(1L)))
            throw new IllegalStateException("Event start date cannot be behind the current time");

        XValidUserRes res = null;
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer "+authToken);
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            res = mrest.exchange(
                    config.GatewayBaseUri+"/api/v1/user/isvalid/email/{userId}",
                    HttpMethod.GET,
                    requestEntity,
                    XValidUserRes.class,
                    currentUserEmail).getBody();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to continue with request, owning user can't be verified");
        }

        if (res == null)
            throw new RuntimeException("Couldn't verify owning user genuinely");

        if (!res.exist())
            throw new IllegalStateException("The owning user doesn't exist in the system");

        if (eventRepo.findByTitleAndOwnership(req.getTitle(), res.id()).isPresent())
            throw new DuplicateKeyException("Event already in use");

        //check if ownership has another event during same duration
        Date start = Date.from(req.getStartAt().atZone(ZoneId.of("Africa/Lagos")).toInstant());
        if (eventRepo.findByOwnershipAndDateInBetweenDates(res.id(), start).size() > 0)
            throw new IllegalStateException("One of your event is existing within time window specified in start date");

        res = null;



        Date end = Date.from(req.getStartAt().plusMinutes(req.getDuration()).atZone(ZoneId.of("Africa/Lagos")).toInstant());
//        Event ev = Event.builder()
//                .ownership(res.id())
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

    public Boolean createEventFallback (Exception e) {
        return false;
    }
}
