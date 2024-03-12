package uz.pdp.gymfitnessapp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserResetPasswordDto(@Email(message = "invalid email") String email,
                                   @Size(min = 8, max = 32, message = "password must be between {min} and {max}")
                                   @NotBlank(message = "password cannot be null or blank") String password,
                                   @NotBlank(message = "sms code cannot be null or blank") String emailCode) {}
