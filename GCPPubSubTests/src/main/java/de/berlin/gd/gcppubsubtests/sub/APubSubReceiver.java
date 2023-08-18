package de.berlin.gd.gcppubsubtests.sub;

import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class APubSubReceiver {

    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel_gd_sub")
    public MessageHandler messageReceiver() {
        return message -> {
            processPayload("gd_sub Message arrived! Payload: " + new String((byte[]) message.getPayload()));
            BasicAcknowledgeablePubsubMessage originalMessage =
                    message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
            assert originalMessage != null;
            originalMessage.ack();
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel_gd_sub1")
    public MessageHandler messageReceiver1() {
        return message -> {
            processPayload("gd_sub1 Message arrived! Payload: " + new String((byte[]) message.getPayload()));
            BasicAcknowledgeablePubsubMessage originalMessage =
                    message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
            assert originalMessage != null;
            originalMessage.ack();
        };
    }

    public void processPayload(String message) {
        System.out.println("this: " + this);
        System.out.println(message);
    }

}
