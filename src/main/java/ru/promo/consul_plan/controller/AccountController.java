package ru.promo.consul_plan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.consul_plan.entity.ClientEntity;
import ru.promo.consul_plan.entity.SpecialistEntity;
import ru.promo.consul_plan.service.ClientService;
import ru.promo.consul_plan.service.SpecialistService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private SpecialistService specialistService;

    @Autowired
    private ClientService clientService;

    //Под авторизацией всем
    //    @GetMapping("/all-auth")
    //    public String getInfo(@AuthenticationPrincipal UserDetails user) {
    //        return user.getUsername();
    //    }

    //Только админу
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getInfoForAdmin(@AuthenticationPrincipal UserDetails user) {
        return user.getUsername();
    }

    @GetMapping("/admin/specialists")
    public ResponseEntity<List<SpecialistEntity>> getAllSpecialists() {
        List<SpecialistEntity> specialists = specialistService.getAll();
        return ResponseEntity.ok(specialists);
    }

    @GetMapping("/admin/clients")
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        List<ClientEntity> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }
}
