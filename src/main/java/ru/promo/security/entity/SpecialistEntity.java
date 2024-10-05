package ru.promo.security.entity;


import jakarta.persistence.*;
import lombok.Data;
import ru.promo.security.domain.entity.Account;

@Data
@Entity
@Table(name = "specialist")
public class SpecialistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String specialization;
}