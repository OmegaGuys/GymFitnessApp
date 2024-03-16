package uz.pdp.gymfitnessapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.dto.NotificationDto;
import uz.pdp.gymfitnessapp.dto.NotificationResDto;
import uz.pdp.gymfitnessapp.service.NotificationService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + NotificationController.BASE_URL)
@RequiredArgsConstructor
public class NotificationController {
    public static final String BASE_URL = "/notification";
    private final NotificationService service;

    @PostMapping
    public ApiResponse<NotificationResDto> notify(@RequestParam(required = false) boolean notifyAll,
                                                  @RequestBody @Valid NotificationDto notificationDto) {
        return ApiResponse.body(service.notify(notifyAll, notificationDto));
    }

    @DeleteMapping("/{notificationId}/{userId}")
    public ApiResponse<?> deleteNotifOfUser(@PathVariable UUID notificationId) {
        service.deleteNotifOfUser(notificationId);
        return ApiResponse.respond(false, "Successfully deleted");
    }
}
