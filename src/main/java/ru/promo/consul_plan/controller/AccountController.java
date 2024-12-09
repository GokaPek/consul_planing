package ru.promo.consul_plan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.consul_plan.domain.Client;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.service.ClientService;
import ru.promo.consul_plan.service.SpecialistService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController implements AccountApi {
    private final SpecialistService specialistService;
    private final ClientService clientService;

    //Под авторизацией всем
    //    @GetMapping("/all-auth")
    //    public String getInfo(@AuthenticationPrincipal UserDetails user) {
    //        return user.getUsername();
    //    }

    //Только админу
    @Override
    public String getInfoForAdmin(UserDetails user) {
        log.debug("Request to get info for admin: {}", user.getUsername());
        return user.getUsername();
    }

    @Override
    public ResponseEntity<List<Specialist>> getAllSpecialists() {
        log.debug("Request to get all specialists");
        List<Specialist> specialists = specialistService.getAll();
        return ResponseEntity.ok(specialists);
    }

    @Override
    public ResponseEntity<List<Client>> getAllClients() {
        log.debug("Request to get all clients");
        List<Client> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }
}
