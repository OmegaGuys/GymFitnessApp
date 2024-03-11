package uz.pdp.gymfitnessapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ReviewDto {
    @NotNull
    private UUID trainerId;
    private String comment;
    private double rate;
    @NotEmpty
    private String reviewType;
}
