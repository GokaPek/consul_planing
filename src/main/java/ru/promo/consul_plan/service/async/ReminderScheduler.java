package ru.promo.consul_plan.service.async;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.domain.entity.TypeStatus;
import ru.promo.consul_plan.repository.ConsultationRepository;
import ru.promo.consul_plan.service.ConsultationService;
import ru.promo.consul_plan.service.NotificationService;
import ru.promo.consul_plan.service.ScheduleService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderScheduler {
    private final ScheduleService scheduleService;
    private final NotificationService notificationService;
    private final ConsultationService consultationService;

    private final ConsultationRepository consultationRepository;



    // Запускать каждый день в 12:00
    @Scheduled(cron = "0 0 12 * * ?")
    public void sendDailyReminders() throws ChangeSetPersister.NotFoundException {
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);

        // Найти все расписания на завтра
        List<Schedule> dtos = scheduleService.findAllByDateTimeBetween(tomorrow);

        for (Schedule dto : dtos) {
            // Найти все консультации для клиента
            var consultations = consultationRepository.findByClientId(dto.getClientId());
            for (ConsultationEntity consultation : consultations) {
                // Отправить напоминание, если консультация подтверждена
                if (consultation.getStatus() == TypeStatus.CONFORMED) {
                    notificationService.sendReminder(consultation);
                    consultation.setReminderSent(true);
                    consultationService.update(consultation);
                }
            }
        }
    }
}
