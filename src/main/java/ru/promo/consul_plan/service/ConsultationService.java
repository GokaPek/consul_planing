package ru.promo.consul_plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.dto.ScheduleDTO;
import ru.promo.consul_plan.entity.ConsultationEntity;
import ru.promo.consul_plan.entity.NotificationEntity;
import ru.promo.consul_plan.entity.ScheduleEntity;
import ru.promo.consul_plan.repository.ConsultationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService implements IConsultationService {

    private final ConsultationRepository consultationRepository;
    private final NotificationService notificationService;
    private final ScheduleService scheduleService;
    private final ClientService clientService;

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

        var schedule = scheduleService.getById(scheduleId);
        var client = clientService.getById(clientId);

        ConsultationEntity consultation = new ConsultationEntity();

        consultation.setClient(client);
        consultation.setSchedule(schedule);
        consultation.setSpecialist(schedule.getSpecialist());
        consultation.setReminderSent(false);
        consultation.setStatus("reserved");
        ConsultationEntity reservedConsultation = consultationRepository.save(consultation);

        // Создание уведомления о резервировании консультации
        NotificationEntity notification = new NotificationEntity();
        notification.setConsultation(reservedConsultation);
        notification.setType("reservation");
        notification.setSentDateTime(LocalDateTime.now());
        notification.setStatus("sent");
        notificationService.create(notification);

        schedule.setClient(client);

        scheduleService.update(schedule);

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
            consultation.setStatus("confirmed");
            ConsultationEntity confirmedConsultation = consultationRepository.save(consultation);

            // Создание уведомления о подтверждении консультации
            NotificationEntity notification = new NotificationEntity();
            notification.setConsultation(confirmedConsultation);
            notification.setType("confirmation");
            notification.setSentDateTime(LocalDateTime.now());
            notification.setStatus("sent");
            notificationService.create(notification);
            notificationService.sendReminder(consultation);

            return confirmedConsultation;
        }
        return null;
    }

    @Override
    public ConsultationEntity cancelConsultation(Long consultationId) {
        ConsultationEntity consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null) {
            consultation.setStatus("cancelled");
            ConsultationEntity cancelledConsultation = consultationRepository.save(consultation);

            // Создание уведомления об отмене консультации
            NotificationEntity notification = new NotificationEntity();
            notification.setConsultation(cancelledConsultation);
            notification.setType("cancellation");
            notification.setSentDateTime(LocalDateTime.now());
            notification.setStatus("sent");
            notificationService.create(notification);

            ScheduleEntity schedule = consultation.getSchedule();
            schedule.setClient(null);

            scheduleService.update(schedule);

            return cancelledConsultation;
        }
        return null;
    }

    // автоматические напоминания
    @Scheduled(cron = "0 0 12 * * ?") // Запускать каждый день в 12:00
    public void sendDailyReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        List<ScheduleEntity> entities = scheduleService.findAllByDateTimeBetween(tomorrow.toLocalDate());

        for (ScheduleEntity entity : entities){
            var consultations = consultationRepository.findByClientId(entity.getClient().getId());
            for (ConsultationEntity consultation : consultations) {
                if (consultation.getStatus() == "confirmed"){
                    notificationService.sendReminder(consultation);
                    consultation.setReminderSent(true);
                }
            }
        }
    }
}