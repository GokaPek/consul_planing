package ru.promo.consul_plan.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kafka-topic")
public class KafkaTopicProperties {
    private String consultationTopic;
}
