package uz.pdp.gymfitnessapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardUpdateDto {
    @NotNull
    private String userName;
    private boolean isDefault;
}
