package uz.pdp.gymfitnessapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.common.DataLoader;
import uz.pdp.gymfitnessapp.entity.Card;
import uz.pdp.gymfitnessapp.entity.Subscription;
import uz.pdp.gymfitnessapp.entity.User;
import uz.pdp.gymfitnessapp.entity.enums.SubscriptionType;
import uz.pdp.gymfitnessapp.repository.UserRepository;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final UserRepository userRepository;

    public void subscribe(UUID userid, UUID cardId, String subscriptionType) {
        // todo get user from SecurityContextHolder
        User user = userRepository.findById(userid).orElseThrow(() -> ApiException.throwException("User not found"));
        // todo if user has a MONTHLY and wants YEARLY ...
        if (Objects.nonNull(user.getSubscription()))
            throw ApiException.throwException("You already have a subscription");

        Card card = getCardFrom(user, cardId);
        Subscription subscription = getSubscription(subscriptionType);

        if (card.getBalance() < subscription.getPrice())
            throw ApiException.throwException("Insufficient funds");

        card.setBalance(card.getBalance() - subscription.getPrice());
        user.setSubscription(subscription);
        user.setSubscribeDate(LocalDate.now());
        user.getCards().set(user.getCards().indexOf(card), card);
        userRepository.save(user);
    }

    private Subscription getSubscription(String subscriptionType) {
        try {
            SubscriptionType.valueOf(subscriptionType);
        } catch (IllegalArgumentException e) {
            throw ApiException.throwException("Wrong subscription type");
        }
        return subscriptionType.startsWith("Y") ? DataLoader.getYearlySub() : DataLoader.getMonthlySub();
    }

    private Card getCardFrom(User user, UUID cardId) {
        for (var card : user.getCards()) {
            if (card.getId().equals(cardId))
                return card;
        }
        throw ApiException.throwException("Card not found");
    }
}
