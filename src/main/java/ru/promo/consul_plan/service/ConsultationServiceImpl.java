package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Consultation;
import ru.promo.consul_plan.domain.entity.*;
import ru.promo.consul_plan.mapper.ConsultationEntityMapper;
import ru.promo.consul_plan.mapper.ConsultationMapper;
import ru.promo.consul_plan.repository.ConsultationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    private final NotificationService notificationService;
    private final ScheduleService scheduleService;
    private final ClientService clientService;

    private final ConsultationMapper consultationMapper;
    private final ConsultationEntityMapper consultationEntityMapper;

    @Override
    @Transactional
    public void create(Consultation dto) {
        consultationRepository.save(consultationEntityMapper.toEntity(dto));
    }

    @Override
    @Transactional
    public ConsultationEntity getById(Long id) {
        return consultationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Consultation reserveConsultation(Long scheduleId, Long clientId) throws ChangeSetPersister.NotFoundException {

        var schedule = scheduleService.getById(scheduleId);
        var client = clientService.getEntityById(clientId);

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
        notification.setStatus(NotificationType.SENT);
        notificationService.create(notification);

        schedule.setClient(client);

        scheduleService.update(schedule);

        return consultationMapper.toDTO(reservedConsultation);
    }

    @Override
    @Transactional
    public List<Consultation> getClientConsultations(Long clientId) {
        return consultationMapper.toDTOList(consultationRepository.findByClientId(clientId));
    }

    @Override
    @Transactional
    public List<Consultation> getSpecialistConsultations(Long specialistId) {
        return consultationMapper.toDTOList(consultationRepository.findBySpecialistId(specialistId));
    }

    @Override
    @Transactional
    public Consultation confirmConsultation(Long consultationId) throws ChangeSetPersister.NotFoundException {
        ConsultationEntity consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null) {
            consultation.setStatus(TypeStatus.CONFORMED);
            ConsultationEntity confirmedConsultation = consultationRepository.save(consultation);

            // Создание уведомления о подтверждении консультации
            NotificationEntity notification = new NotificationEntity();
            notification.setConsultation(confirmedConsultation);
            notification.setType(TypeStatus.CONFORMED);
            notification.setSentDateTime(LocalDateTime.now());
            notification.setStatus(NotificationType.SENT);
            notificationService.create(notification);
            notificationService.sendReminder(consultation);

            return consultationMapper.toDTO(confirmedConsultation);
        }
        return null;
    }

    @Override
    @Transactional
    public Consultation cancelConsultation(Long consultationId) {
        ConsultationEntity consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null) {
            consultation.setStatus(TypeStatus.CANCELLED);
            ConsultationEntity cancelledConsultation = consultationRepository.save(consultation);

            // Создание уведомления об отмене консультации
            NotificationEntity notification = new NotificationEntity();
            notification.setConsultation(cancelledConsultation);
            notification.setType(TypeStatus.CANCELLED);
            notification.setSentDateTime(LocalDateTime.now());
            notification.setStatus(NotificationType.SENT);
            notificationService.create(notification);

            ScheduleEntity schedule = consultation.getSchedule();
            schedule.setClient(null);

            scheduleService.update(schedule);

            return consultationMapper.toDTO(cancelledConsultation);
        }
        return null;
    }

    //TODO
    // автоматические напоминания, будет доделано после
    /*
    @Scheduled(cron = "0 0 12 * * ?") // Запускать каждый день в 12:00
    public void sendDailyReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        List<ScheduleEntity> entities = scheduleService.findAllByDateTimeBetween(tomorrow.toLocalDate());

        for (ScheduleEntity entity : entities) {
            var consultations = consultationRepository.findByClientId(entity.getClient().getId());
            for (ConsultationEntity consultation : consultations) {
                if (consultation.getStatus() == TypeStatus.CONFORMED) {
                    notificationService.sendReminder(consultation);
                    consultation.setReminderSent(true);
                }
            }
        }
    }

     */
}