package ru.promo.consul_plan.service.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.consul_plan.domain.ConsultationEvent;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.service.ConsultationService;

@Service
@Slf4j
@RequiredArgsConstructor
public class RetryNotificationScheduler {

    private final ConsultationService consultationService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void retryFailedNotifications() {
        log.info("Starting retry for failed notifications");

        int page = 0;
        int size = 50;

        Page<ConsultationEntity> consultations;

        do {
            consultations = consultationService.getNotificationCreatedFalse(page, size);
            for (ConsultationEntity consultation : consultations) {
                ConsultationEvent event = consultationService.createConsultationEvent(consultation);

                kafkaTemplate.send("consultation-topic", event).whenComplete((result, ex) -> {
                    if (ex == null) {
                        consultation.setNotificationCreated(true);
                        log.info("Retry successful for consultation: ID={}", consultation.getId());
                    } else {
                        log.error("Retry failed for consultation: ID={}", consultation.getId(), ex);
                    }
                    consultationService.update(consultation);
                });
            }
            page++;
        } while (!consultations.isEmpty());

        log.info("Retry for failed notifications completed");
    }
}