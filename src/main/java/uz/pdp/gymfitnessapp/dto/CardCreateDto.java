package uz.pdp.gymfitnessapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CardCreateDto {
    @NotNull
    @NotBlank
    private String userName;
    @NotNull
    @NotBlank
    private String number;
    @NotNull
    @NotBlank
    private LocalDate expiration;
    @NotNull
    @NotBlank
    private String cvc;
    @NotNull
    @NotBlank
    private String type;
    private boolean isDefault;
}
