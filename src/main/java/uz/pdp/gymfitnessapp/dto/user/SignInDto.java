package uz.pdp.gymfitnessapp.dto.user;

import jakarta.validation.constraints.Email;
import lombok.NonNull;

public record SignInDto(@NonNull @Email(message = "invalid email entered") String email,
                        @NonNull String password){
}