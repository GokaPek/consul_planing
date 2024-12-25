package ru.promo.consul_plan.config.properties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "security.config")
public class SecurityProperties {

    @NotEmpty
    private List<String> allowedOriginPatterns;
    @NotEmpty
    private List<String> allowedMethods;
    @NotEmpty
    private List<String> allowedHeaders;
    @NotNull
    private Boolean allowCredentials;
    @NotEmpty
    private List<String> permitAllEndpoints;
    @NotEmpty
    private List<String> authenticatedEndpoints;
}
