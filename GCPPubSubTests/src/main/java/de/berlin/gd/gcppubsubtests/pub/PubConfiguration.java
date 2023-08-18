package de.berlin.gd.gcppubsubtests.pub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class PubConfiguration {

    @Bean
    @ServiceActivator(inputChannel = "pubsubOutputChannel_gd_topic")
    public MessageHandler messageHandlerGdTopic(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, "gd-topic");
    }

    @MessagingGateway(defaultRequestChannel = "pubsubOutputChannel_gd_topic")
    public interface PublishGdTopicGateway {
        void sendToPubsub(String text);
    }


}
