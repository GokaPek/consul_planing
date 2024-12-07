package ru.promo.consul_plan.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.domain.entity.NotificationEntity;
import ru.promo.consul_plan.domain.entity.NotificationType;
import ru.promo.consul_plan.domain.entity.TypeStatus;
import ru.promo.consul_plan.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    @Override
    public void create(NotificationEntity entity) {
        notificationRepository.save(entity);
    }

    @Override
    public NotificationEntity getById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public void update(NotificationEntity entity) {
        if (notificationRepository.existsById(entity.getId())) {
            notificationRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<NotificationEntity> getAllByConsultationId(Long consultationId) {
        return notificationRepository.findAllByConsultationId(consultationId);
    }

    @Override
    public List<NotificationEntity> getAllByClientId(Long clientId) {
        return notificationRepository.findAllByConsultationClientId(clientId);
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
            String email = client.getAccount().getUsername();
            String subject = "Напоминание о консультации";
            String text = "Уважаемый пользователь, напоминаем вам о предстоящей консультации у специалиста " + consultation.getSpecialist().getAccount().getUsername();
            emailService.sendEmail(email, subject, text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}