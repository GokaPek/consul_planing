package ru.promo.consul_plan.config.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kafka-topic")
public class KafkaTopicProperties {
    @NotNull
    private String consultationTopic;
}
