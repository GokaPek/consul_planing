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

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountService accountService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public TokenResponse signUp(SignUpRequest request) {

        var user = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        accountService.create(user);

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
