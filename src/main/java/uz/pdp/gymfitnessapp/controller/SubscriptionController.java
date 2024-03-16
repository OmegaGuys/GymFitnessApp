package uz.pdp.gymfitnessapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.service.SubscriptionService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + "/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService service;

    @PostMapping(value = "/{userId}/{cardId}", params = {"subscriptionType"})
    public ApiResponse<?> subscribe(@PathVariable UUID cardId,
                                    @RequestParam String subscriptionType) {
        service.subscribe(cardId, subscriptionType);
        return ApiResponse.respond(true, "Successfully subscribed");
    }
}
