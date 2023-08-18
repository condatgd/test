package de.berlin.gd.gcppubsubtests.sub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class SubConfiguration {

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
    public PubSubInboundChannelAdapter pubsubAdapter_gd_sub1(
            @Qualifier("pubsubInputChannel_gd_sub1") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, "gd-sub1");
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }

    @Bean
    public MessageChannel pubsubInputChannel_gd_sub() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel pubsubInputChannel_gd_sub1() {
        return new DirectChannel();
    }

}
