package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.client.NotificationClient;
import ru.promo.consul_plan.domain.Consultation;
import ru.promo.consul_plan.domain.SendReminderRequest;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;
import ru.promo.consul_plan.domain.entity.TypeStatus;
import ru.promo.consul_plan.exception.NotFoundException;
import ru.promo.consul_plan.mapper.ConsultationEntityMapper;
import ru.promo.consul_plan.mapper.ConsultationMapper;
import ru.promo.consul_plan.repository.ConsultationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    private final ScheduleService scheduleService;
    private final ClientService clientService;

    private final ConsultationMapper consultationMapper;
    private final ConsultationEntityMapper consultationEntityMapper;

    private final NotificationClient notificationClient;

    @Override
    @Transactional
    public void create(Consultation dto) {
        consultationRepository.save(consultationEntityMapper.toEntity(dto));
    }

    @Override
    @Transactional
    public void update(ConsultationEntity entity) {
        consultationRepository.save(entity);
    }


    @Override
    @Transactional
    public ConsultationEntity getById(Long id) {
        return consultationRepository.findById(id).orElseThrow(() -> new NotFoundException("Consultation not found with id: " + id));
    }

    @Override
    @Transactional
    public Consultation reserveConsultation(Long scheduleId, Long clientId) {
        var client = clientService.getEntityById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + clientId));
        var schedule = scheduleService.getById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Schedule not found with id: " + scheduleId));

        ConsultationEntity consultation = new ConsultationEntity();
        consultation.setClient(client);
        consultation.setSchedule(schedule);
        consultation.setSpecialist(schedule.getSpecialist());
        consultation.setReminderSent(false);
        consultation.setStatus(TypeStatus.RESERVED);
        ConsultationEntity reservedConsultation = consultationRepository.save(consultation);


        // Резервирование расписания
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
    public Consultation confirmConsultation(Long consultationId) {
        ConsultationEntity consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new NotFoundException("Consultation not found with id: " + consultationId));

        consultation.setStatus(TypeStatus.CONFIRMED);
        ConsultationEntity confirmedConsultation = consultationRepository.save(consultation);

        // Отправка уведомления через микросервис уведомлений
        notificationClient.sendReminder(new SendReminderRequest(
                confirmedConsultation.getId(),
                confirmedConsultation.getClient().getAccountEntity().getUsername(),
                confirmedConsultation.getSpecialist().getAccountEntity().getUsername()
        ));

        return consultationMapper.toDTO(confirmedConsultation);
    }

    @Override
    @Transactional
    public Consultation cancelConsultation(Long consultationId) {
        ConsultationEntity consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new NotFoundException("Consultation not found with id: " + consultationId));

        consultation.setStatus(TypeStatus.CANCELLED);
        ConsultationEntity cancelledConsultation = consultationRepository.save(consultation);

        // Отправка уведомления через микросервис уведомлений
        notificationClient.sendReminder(new SendReminderRequest(
                cancelledConsultation.getId(),
                cancelledConsultation.getClient().getAccountEntity().getUsername(), // clientEmail
                cancelledConsultation.getSpecialist().getAccountEntity().getUsername() // specialistEmail
        ));

        // Освобождение расписания
        ScheduleEntity schedule = consultation.getSchedule();
        schedule.setClient(null);
        scheduleService.update(schedule);

        return consultationMapper.toDTO(cancelledConsultation);
    }


    @Override
    public void markReminderSent(Long consultationId) {
        ConsultationEntity consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new NotFoundException("Consultation not found"));
        consultation.setReminderSent(true);
        consultationRepository.save(consultation);
    }
}