package uz.pdp.gymfitnessapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NotificationDto {
    @NotEmpty
    private UUID userId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    private boolean isAboutEvent;
}
