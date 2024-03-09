package uz.pdp.gymfitnessapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.gymfitnessapp.entity.Subscription;
import uz.pdp.gymfitnessapp.entity.enums.SubscriptionType;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionType> {
}
