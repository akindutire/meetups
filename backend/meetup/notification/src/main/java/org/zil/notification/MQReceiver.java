package org.zil.notification;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class MQReceiver {

    @RabbitListener(queues = {"NOTIF_MQ"})
    public void receiveMessage(@Payload HashMap<String, String> message) {
        if (Objects.equals(NotifType.EMAIL.toString(), message.get("type"))) {
            System.out.println("Email Message:" + message.get("message"));
        }
        System.out.println("Message:"+message.get("message"));
    }
}
