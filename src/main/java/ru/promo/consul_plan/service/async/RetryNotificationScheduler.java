package ru.promo.consul_plan.service.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.consul_plan.domain.ConsultationEvent;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.repository.ConsultationRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RetryNotificationScheduler {

    private final ConsultationRepository consultationRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void retryFailedNotifications() {
        log.info("Starting retry for failed notifications");

        List<ConsultationEntity> consultations = consultationRepository.findByNotificationCreatedFalse();

        for (ConsultationEntity consultation : consultations) {
            ConsultationEvent event = new ConsultationEvent();
            event.setConsultationId(consultation.getId());
            event.setClientEmail(consultation.getClient().getAccountEntity().getUsername());
            event.setSpecialistEmail(consultation.getSpecialist().getAccountEntity().getUsername());
            event.setConsultationDate(consultation.getSchedule().getStartTime().toLocalDate());

            kafkaTemplate.send("consultation-topic", event).whenComplete((result, ex) -> {
                if (ex == null) {

                    consultation.setNotificationCreated(true);
                    log.info("Retry successful for consultation: ID={}", consultation.getId());
                } else {

                    log.error("Retry failed for consultation: ID={}", consultation.getId(), ex);
                }
                consultationRepository.save(consultation);
            });
        }

        log.info("Retry for failed notifications completed");
    }
}