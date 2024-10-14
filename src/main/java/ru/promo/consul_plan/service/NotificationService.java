package ru.promo.consul_plan.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.dto.NotificationDTO;
import ru.promo.consul_plan.entity.ConsultationEntity;
import ru.promo.consul_plan.entity.NotificationEntity;
import ru.promo.consul_plan.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;
    @Autowired
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
    public List<NotificationDTO> getAllByConsultationId(Long consultationId) {
        List<NotificationEntity> entities = notificationRepository.findAllByConsultationId(consultationId);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getAllByClientId(Long clientId) {
        List<NotificationEntity> entities = notificationRepository.findAllByConsultationClientId(clientId);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

//    @Override
//    public void sendReminder(Long consultationId) {
//        // Логика отправки напоминания
//        NotificationEntity reminder = new NotificationEntity();
//        reminder.setConsultation(new ConsultationEntity());
//        reminder.setId(consultationId);
//        reminder.setType("reminder");
//        reminder.setSentDateTime(LocalDateTime.now());
//        reminder.setStatus("sent");
//        notificationRepository.save(reminder);
//    }

    @Override
    public void sendReminder(ConsultationEntity consultation) {



        // Логика отправки напоминания
        NotificationEntity reminder = new NotificationEntity();
        reminder.setConsultation(consultation);
        reminder.setId(consultation.getId());
        reminder.setType("reminder");
        reminder.setSentDateTime(LocalDateTime.now());
        reminder.setStatus("sent");
        notificationRepository.save(reminder);

        // Отправка уведомления по электронной почте
        try {
            String email = "slind339@gmail.com"; // Замените на реальный email пользователя
            String subject = "Напоминание о консультации";
            String text = "Уважаемый пользователь, напоминаем вам о предстоящей консультации.";
            emailService.sendEmail(email, subject, text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private NotificationDTO convertToDTO(NotificationEntity entity) {
        if (entity == null) {
            return null;
        }
        NotificationDTO dto = new NotificationDTO();
        dto.setId(entity.getId());
        dto.setConsultationId(entity.getConsultation().getId());
        dto.setType(entity.getType());
        dto.setSentDateTime(entity.getSentDateTime());
        dto.setStatus(entity.getStatus());
        return dto;
    }


}