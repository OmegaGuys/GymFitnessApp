package uz.pdp.gymfitnessapp.dto;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.gymfitnessapp.entity.enums.ReviewType;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewResponseDto {
    private double rate;
    private String comment;
    private LocalDateTime leftDate;
    private ReviewType type;
}
