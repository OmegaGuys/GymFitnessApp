package uz.pdp.gymfitnessapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.gymfitnessapp.entity.enums.CardType;

import java.time.LocalDate;

@Getter
@Setter
public class CardCreateDto {
    @NotNull
    private String userName;
    @NotNull
    private String number;
    @NotNull
    private LocalDate expiration;
    @NotNull
    private String cvc;
    @NotNull
    private CardType type;
    private boolean isDefault;
}
