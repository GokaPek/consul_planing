package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.promo.consul_plan.domain.entity.ClientEntity;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

import java.util.List;

@Tag(name = "Account API", description = "API для управления учетными записями")
@RequestMapping("/api/account")
public interface AccountApi {

    @Operation(summary = "Получить информацию о пользователе (только для админа)")
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    String getInfoForAdmin(@AuthenticationPrincipal UserDetails user);

    @Operation(summary = "Получить всех специалистов (только для админа)")
    @GetMapping("/admin/specialists")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<SpecialistEntity>> getAllSpecialists();

    @Operation(summary = "Получить всех клиентов (только для админа)")
    @GetMapping("/admin/clients")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<ClientEntity>> getAllClients();
}