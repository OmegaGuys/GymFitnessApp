package uz.pdp.gymfitnessapp.common;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.gymfitnessapp.entity.Subscription;
import uz.pdp.gymfitnessapp.entity.enums.SubscriptionType;
import uz.pdp.gymfitnessapp.repository.SubscriptionRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final SubscriptionRepository subscriptionRepository;
    private static Subscription MONTHLY;
    private static Subscription YEARLY;

    @Override
    public void run(String... args) throws Exception {
        DataLoader.YEARLY = subscriptionRepository.save(new Subscription(SubscriptionType.YEARLY, 100.99));
        DataLoader.MONTHLY = subscriptionRepository.save(new Subscription(SubscriptionType.MONTHLY, 15.99));
    }

    public static Subscription getMonthlySub() {
        return MONTHLY;
    }

    public static Subscription getYearlySub() {
        return YEARLY;
    }
}
