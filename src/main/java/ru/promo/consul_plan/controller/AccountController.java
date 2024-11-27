package ru.promo.consul_plan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.consul_plan.domain.entity.ClientEntity;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;
import ru.promo.consul_plan.service.ClientService;
import ru.promo.consul_plan.service.SpecialistService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController implements AccountApi {
    private SpecialistService specialistService;
    private ClientService clientService;

    //Под авторизацией всем
    //    @GetMapping("/all-auth")
    //    public String getInfo(@AuthenticationPrincipal UserDetails user) {
    //        return user.getUsername();
    //    }

    //Только админу
    @Override
    public String getInfoForAdmin(@AuthenticationPrincipal UserDetails user) {
        log.debug("Request to get info for admin: {}", user.getUsername());
        return user.getUsername();
    }
    @Override
    public ResponseEntity<List<SpecialistEntity>> getAllSpecialists() {
        log.debug("Request to get all specialists");
        List<SpecialistEntity> specialists = specialistService.getAll();
        return ResponseEntity.ok(specialists);
    }
    @Override
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        log.debug("Request to get all clients");
        List<ClientEntity> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }
}
