package ru.promo.consul_plan.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Notification;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.domain.entity.NotificationEntity;
import ru.promo.consul_plan.domain.entity.NotificationType;
import ru.promo.consul_plan.domain.entity.TypeStatus;
import ru.promo.consul_plan.mapper.NotificationMapper;
import ru.promo.consul_plan.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    private final NotificationMapper notificationMapper;

    @Override
    public void create(Notification dto) {
        notificationRepository.save(notificationMapper.toEntity(dto));
    }

    @Override
    public void create(NotificationEntity entity) {
        notificationRepository.save(entity);
    }

    @Override
    public Notification getById(Long id) {
        return notificationMapper.toDTO(notificationRepository.findById(id).orElse(null));
    }

    @Override
    public void update(Notification dto) {
        if (notificationRepository.existsById(dto.getId())) {
            notificationRepository.save(notificationMapper.toEntity(dto));
        }
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<Notification> getAllByConsultationId(Long consultationId) {
        return notificationMapper.toDTOList(notificationRepository.findAllByConsultationId(consultationId));
    }

    @Override
    public List<Notification> getAllByClientId(Long clientId) {
        return notificationMapper.toDTOList(notificationRepository.findAllByConsultationClientId(clientId));
    }

    @Override
    public void sendReminder(ConsultationEntity consultation) {

        var client = consultation.getClient();

        // Логика отправки напоминания
        NotificationEntity reminder = new NotificationEntity();
        reminder.setConsultation(consultation);
        reminder.setId(consultation.getId());
        reminder.setType(TypeStatus.REMAINED);
        reminder.setSentDateTime(LocalDateTime.now());
        reminder.setStatus(NotificationType.SENT);
        notificationRepository.save(reminder);

        String massage;

        // Отправка уведомления по электронной почте
        try {
            String email = client.getAccountEntity().getUsername();
            String subject = "Напоминание о консультации";
            String text = "Уважаемый пользователь, напоминаем вам о предстоящей консультации у специалиста " + consultation.getSpecialist().getAccountEntity().getUsername();
            emailService.sendEmail(email, subject, text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}