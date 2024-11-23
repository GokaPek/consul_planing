package ru.promo.consul_plan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.consul_plan.domain.entity.ClientEntity;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;
import ru.promo.consul_plan.service.ClientServiceImpl;
import ru.promo.consul_plan.service.SpecialistServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    @Autowired
    private SpecialistServiceImpl specialistServiceImpl;

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    //Под авторизацией всем
    //    @GetMapping("/all-auth")
    //    public String getInfo(@AuthenticationPrincipal UserDetails user) {
    //        return user.getUsername();
    //    }

    //Только админу
    public String getInfoForAdmin(@AuthenticationPrincipal UserDetails user) {
        return user.getUsername();
    }

    public ResponseEntity<List<SpecialistEntity>> getAllSpecialists() {
        List<SpecialistEntity> specialists = specialistServiceImpl.getAll();
        return ResponseEntity.ok(specialists);
    }

    public ResponseEntity<List<ClientEntity>> getAllClients() {
        List<ClientEntity> clients = clientServiceImpl.getAll();
        return ResponseEntity.ok(clients);
    }
}
