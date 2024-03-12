package uz.pdp.gymfitnessapp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.dto.user.SignInDto;
import uz.pdp.gymfitnessapp.dto.user.SignUpDto;
import uz.pdp.gymfitnessapp.dto.user.UserResetPasswordDto;
import uz.pdp.gymfitnessapp.dto.user.UserResponseDto;
import uz.pdp.gymfitnessapp.entity.User;
import uz.pdp.gymfitnessapp.repository.UserRepository;
import uz.pdp.gymfitnessapp.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final EmailCodeService emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final ModelMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getByEmail(email);
    }

    public UserResponseDto signUp(SignUpDto signUpDto) {
        String email = signUpDto.getEmail();
        if (userRepository.existsByEmail(email))
            throw ApiException.throwException("%s email already registered!".formatted(email));
        if (!signUpDto.getPassword().equals(signUpDto.getConfirmPassword())){
            throw ApiException.throwException("Password and confirm password must be same!");
        }
        User user = mapper.map(signUpDto, User.class);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userRepository.save(user);
        return mapper.map(user, UserResponseDto.class);    }

    public UserResponseDto signIn(SignInDto signInDto) {
        String email = signInDto.email();
        User user = getByEmail(email);
        if (!passwordEncoder.matches(signInDto.password(), user.getPassword())) {
            throw ApiException.throwException("Email or password is incorrect");
        }
        UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
        userResponseDto.setToken(tokenProvider.generateToken(email));
        return userResponseDto;
    }

    public String resendSmsCode(String email) {
        emailService.sendVerificationCode(email);
        return "Verification code has been sent to %s successfully".formatted(email);
    }

    public String resetPassword(UserResetPasswordDto resetPasswordDto) {
        String email = resetPasswordDto.email();
        if (!emailService.verifyEmail(email, resetPasswordDto.emailCode())) {
            throw  ApiException.throwException("verification failed");
        }
        User user = getByEmail(email);
        user.setPassword(passwordEncoder.encode(resetPasswordDto.password()));
        userRepository.save(user);

        return "password successfully reset!";
    }

    private User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> ApiException.throwException("user not found"));
    }
}
