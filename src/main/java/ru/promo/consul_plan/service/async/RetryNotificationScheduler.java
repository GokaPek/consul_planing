package ru.promo.consul_plan.service.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.consul_plan.config.properties.KafkaTopicProperties;
import ru.promo.consul_plan.domain.ConsultationEvent;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.service.ConsultationService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "retry.scheduler",
        name = "enabled",
        havingValue = "true")
public class RetryNotificationScheduler {

    private static final int PAGE_SIZE = 50;
    private final ConsultationService consultationService;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;

    @Scheduled(cron = "${retry.scheduler.cron}")
    @Transactional
    public void retryFailedNotifications() {
        log.info("Starting retry for failed notifications");

        List<ConsultationEntity> consultations;
        consultations = consultationService.getNotificationCreatedFalse(0, PAGE_SIZE);
        consultations.forEach(consultation -> {
            ConsultationEvent event = consultationService.createConsultationEvent(consultation, consultation.getStatus());

            kafkaTemplate.send(kafkaTopicProperties.getConsultationTopic(), event).whenComplete((result, ex) -> {
                if (ex == null) {
                    consultation.setNotificationCreated(true);
                    log.info("Retry successful for consultation: ID={}", consultation.getId());
                    consultationService.update(consultation);
                } else {
                    log.error("Retry failed for consultation: ID={}", consultation.getId(), ex);
                }
            });
        });

        log.info("Retry for failed notifications completed");
    }
}
