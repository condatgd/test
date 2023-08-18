package de.berlin.gd.gcppubsubtests;

// https://spring.io.xy2401.com/guides/gs/messaging-gcp-pubsub/

// gcloud.cmd pubsub topics create gd-topic --project zdf-de-cloud-test
// gcloud.cmd pubsub subscriptions create gd-sub --topic gd-topic --project zdf-de-cloud-test

// gcloud.cmd pubsub subscriptions delete gd-sub --project zdf-de-cloud-test
// gcloud.cmd pubsub topics delete gd-topic --project zdf-de-cloud-test

// boot 3
// https://www.knowledgefactory.net/2023/03/spring-boot-google-cloud-pubsub-integration-example.html


import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
public class GcppubsubtestsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GcppubsubtestsApplication.class, args);
    }

    /*
    @Bean
    public PubSubInboundChannelAdapter pubsubAdapter_gd_sub(
            @Qualifier("pubsubInputChannel_gd_sub") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, "gd-sub");
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }

    @Bean
    public MessageChannel pubsubInputChannel_gd_sub() {
        return new DirectChannel();
    }


    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel_gd_sub")
    public MessageHandler messageReceiver() {
        return message -> {
            System.out.println("Message arrived! Payload: " + new String((byte[]) message.getPayload()));
            BasicAcknowledgeablePubsubMessage originalMessage =
                    message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
            assert originalMessage != null;
            originalMessage.ack();
        };
    }
    */

    /*
    @Bean
    @ServiceActivator(inputChannel = "pubsubOutputChannel_gd_topic")
    public MessageHandler messageHandlerGdTopic(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, "gd-topic");
    }

    @MessagingGateway(defaultRequestChannel = "pubsubOutputChannel_gd_topic")
    public interface PublishGdTopicGateway {
        void sendToPubsub(String text);
    }
    */
}
