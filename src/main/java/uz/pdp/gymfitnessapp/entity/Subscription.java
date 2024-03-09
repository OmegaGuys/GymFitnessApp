package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import uz.pdp.gymfitnessapp.entity.enums.SubscriptionType;

@Entity
@Getter
public class Subscription {
    @Id
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;
    private double price;
}
