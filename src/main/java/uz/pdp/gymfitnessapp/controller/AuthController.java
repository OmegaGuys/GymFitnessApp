package uz.pdp.gymfitnessapp.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.dto.user.SignInDto;
import uz.pdp.gymfitnessapp.dto.user.SignUpDto;
import uz.pdp.gymfitnessapp.dto.user.UserResetPasswordDto;
import uz.pdp.gymfitnessapp.dto.user.UserResponseDto;
import uz.pdp.gymfitnessapp.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ApiResponse<UserResponseDto> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        return ApiResponse.body(authService.signUp(signUpDto));
    }

    @PostMapping("/sign-in")
    public ApiResponse<UserResponseDto> signIn(@RequestBody SignInDto signInDto) {
        return ApiResponse.body(authService.signIn(signInDto));
    }

    @PostMapping("/forget-password/{email:.*}")
    public ApiResponse<?> forgetPassword(@PathVariable @Email String email){
        String smsCode = authService.sendSmsCode(email);
        return ApiResponse.body(smsCode);

    }

    @PostMapping("/reset-password")
    public ApiResponse<?> verify(@RequestBody @Valid UserResetPasswordDto resetPasswordDto) {
        String response = authService.resetPassword(resetPasswordDto);
        return  ApiResponse.body(response);
    }

}
