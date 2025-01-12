package ru.promo.consul_plan.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.promo.consul_plan.domain.SendReminderRequest;

@FeignClient(name = "notification-service", url = "${feign.client.config.notification-service.url}")
public interface NotificationClient {

    @PostMapping("/api/notifications/reminder")
    void sendReminder(@RequestBody SendReminderRequest request);
}