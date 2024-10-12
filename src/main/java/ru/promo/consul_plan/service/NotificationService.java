package ru.promo.consul_plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.entity.ConsultationEntity;
import ru.promo.consul_plan.entity.NotificationEntity;
import ru.promo.consul_plan.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;

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
    public void sendReminder(Long consultationId) {
        // Логика отправки напоминания
        NotificationEntity reminder = new NotificationEntity();
        reminder.setConsultation(new ConsultationEntity());
        reminder.setId(consultationId);
        reminder.setType("reminder");
        reminder.setSentDateTime(LocalDateTime.now());
        reminder.setStatus("sent");
        notificationRepository.save(reminder);
    }
}