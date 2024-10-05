package ru.promo.consul_plan.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.promo.consul_plan.domain.entity.Account;

@Data
@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
