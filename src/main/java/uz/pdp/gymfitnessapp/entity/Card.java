package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.gymfitnessapp.entity.enums.CardType;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Card extends AbsUUIDEntity {
    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    private LocalDate expiration;

    private double balance;

    @Column(nullable = false)
    private String cvc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType type;

    private boolean isDefault;
}
