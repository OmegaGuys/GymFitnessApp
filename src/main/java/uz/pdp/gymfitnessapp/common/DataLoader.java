package uz.pdp.gymfitnessapp.common;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.gymfitnessapp.entity.Subscription;
import uz.pdp.gymfitnessapp.entity.User;
import uz.pdp.gymfitnessapp.entity.enums.FitnessLevel;
import uz.pdp.gymfitnessapp.entity.enums.Gender;
import uz.pdp.gymfitnessapp.entity.enums.Goal;
import uz.pdp.gymfitnessapp.entity.enums.SubscriptionType;
import uz.pdp.gymfitnessapp.repository.SubscriptionRepository;
import uz.pdp.gymfitnessapp.repository.UserRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private static Subscription MONTHLY;
    private static Subscription YEARLY;

    @Override
    public void run(String... args) throws Exception {
        DataLoader.YEARLY = subscriptionRepository.save(new Subscription(SubscriptionType.YEARLY, 100.99));
        DataLoader.MONTHLY = subscriptionRepository.save(new Subscription(SubscriptionType.MONTHLY, 15.99));
//        userRepository.save(new User("Rakhim DeSanta", "rakhim.des@gmail.com", "123123Ab",
//                Gender.MALE, 63, 165, FitnessLevel.MASTER, Goal.GROW_MUSCLE, LocalDate.now(), null, null, null, null, null));
    }

    public static Subscription getMonthlySub() {
        return MONTHLY;
    }

    public static Subscription getYearlySub() {
        return YEARLY;
    }
}
