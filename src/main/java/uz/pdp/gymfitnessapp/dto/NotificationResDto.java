package uz.pdp.gymfitnessapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationResDto {
    private String title;
    private String text;
    private LocalDateTime leftDate;
    private boolean isAboutEvent;
}
