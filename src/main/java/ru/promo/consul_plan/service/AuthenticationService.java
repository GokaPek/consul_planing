package ru.promo.consul_plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.consul_plan.controller.domain.SignInRequest;
import ru.promo.consul_plan.controller.domain.SignUpRequest;
import ru.promo.consul_plan.controller.domain.TokenResponse;
import ru.promo.consul_plan.domain.Role;
import ru.promo.consul_plan.domain.entity.Account;
import ru.promo.consul_plan.entity.ClientEntity;
import ru.promo.consul_plan.entity.SpecialistEntity;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountService accountService;
    private final SpecialistService specialistService;
    private final ClientService clientService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public TokenResponse signUp(SignUpRequest request) {

        var user = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        accountService.create(user);

        // Создаём профиль рабочего/юзера для акканута
        if (request.getRole() == Role.SPECIALIST) {
            var specialist = new SpecialistEntity();
            specialist.setAccount(user);
            specialist.setSpecialization(request.getSpecialization());
            specialistService.create(specialist);
        } else {
            var client = new ClientEntity();
            client.setAccount(user);
            clientService.create(client);
        }


        var jwt = jwtService.generateToken(user);
        return new TokenResponse(jwt);
    }

    public TokenResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = accountService.userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new TokenResponse(jwt);
    }
}
