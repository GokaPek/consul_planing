package ru.promo.consul_plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.domain.entity.NotificationEntity;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;
import ru.promo.consul_plan.domain.entity.TypeStatus;
import ru.promo.consul_plan.repository.ConsultationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final NotificationServiceImpl notificationServiceImpl;
    private final ScheduleServiceImpl scheduleServiceImpl;
    private final ClientServiceImpl clientServiceImpl;

    @Override
    public void create(ConsultationEntity entity) {
        consultationRepository.save(entity);
    }

    @Override
    public ConsultationEntity getById(Long id) {
        return consultationRepository.findById(id).orElse(null);
    }

    @Override
    public ConsultationEntity reserveConsultation(Long scheduleId, Long clientId) throws ChangeSetPersister.NotFoundException {

        var schedule = scheduleServiceImpl.getById(scheduleId);
        var client = clientServiceImpl.getById(clientId);

        ConsultationEntity consultation = new ConsultationEntity();

        consultation.setClient(client);
        consultation.setSchedule(schedule);
        consultation.setSpecialist(schedule.getSpecialist());
        consultation.setReminderSent(false);
        consultation.setStatus(TypeStatus.RESERVED);
        ConsultationEntity reservedConsultation = consultationRepository.save(consultation);

        // Создание уведомления о резервировании консультации
        NotificationEntity notification = new NotificationEntity();
        notification.setConsultation(reservedConsultation);
        notification.setType(TypeStatus.RESERVED);
        notification.setSentDateTime(LocalDateTime.now());
        notification.setStatus("sent");
        notificationServiceImpl.create(notification);

        schedule.setClient(client);

        scheduleServiceImpl.update(schedule);

        return reservedConsultation;
    }

    @Override
    public List<ConsultationEntity> getClientConsultations(Long clientId) {
        return consultationRepository.findByClientId(clientId);
    }

    @Override
    public List<ConsultationEntity> getSpecialistConsultations(Long specialistId) {
        return consultationRepository.findBySpecialistId(specialistId);
    }

    // TODO
    @Override
    public ConsultationEntity confirmConsultation(Long consultationId) {
        ConsultationEntity consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null) {
            consultation.setStatus(TypeStatus.CONFORMED);
            ConsultationEntity confirmedConsultation = consultationRepository.save(consultation);

            // Создание уведомления о подтверждении консультации
            NotificationEntity notification = new NotificationEntity();
            notification.setConsultation(confirmedConsultation);
            notification.setType(TypeStatus.CONFORMED);
            notification.setSentDateTime(LocalDateTime.now());
            notification.setStatus("sent");
            notificationServiceImpl.create(notification);
            notificationServiceImpl.sendReminder(consultation);

            return confirmedConsultation;
        }
        return null;
    }

    @Override
    public ConsultationEntity cancelConsultation(Long consultationId) {
        ConsultationEntity consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null) {
            consultation.setStatus(TypeStatus.CANCELLED);
            ConsultationEntity cancelledConsultation = consultationRepository.save(consultation);

            // Создание уведомления об отмене консультации
            NotificationEntity notification = new NotificationEntity();
            notification.setConsultation(cancelledConsultation);
            notification.setType(TypeStatus.CANCELLED);
            notification.setSentDateTime(LocalDateTime.now());
            notification.setStatus("sent");
            notificationServiceImpl.create(notification);

            ScheduleEntity schedule = consultation.getSchedule();
            schedule.setClient(null);

            scheduleServiceImpl.update(schedule);

            return cancelledConsultation;
        }
        return null;
    }

    // автоматические напоминания
    @Scheduled(cron = "0 0 12 * * ?") // Запускать каждый день в 12:00
    public void sendDailyReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        List<ScheduleEntity> entities = scheduleServiceImpl.findAllByDateTimeBetween(tomorrow.toLocalDate());

        for (ScheduleEntity entity : entities){
            var consultations = consultationRepository.findByClientId(entity.getClient().getId());
            for (ConsultationEntity consultation : consultations) {
                if (consultation.getStatus() == TypeStatus.CONFORMED){
                    notificationServiceImpl.sendReminder(consultation);
                    consultation.setReminderSent(true);
                }
            }
        }
    }
}