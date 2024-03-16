package uz.pdp.gymfitnessapp.service;

import io.lettuce.core.RedisConnectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.entity.EmailCode;
import uz.pdp.gymfitnessapp.repository.EmailCodeRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailCodeService {

    private final EmailCodeRepository emailCodeRepository;
    private final JavaMailSender mailSender;
    private final String text = "Hi, someone tried to sign up for an Gym-Fitness App account with %s . " +
            "If it was you, enter this confirmation code in the app:" + System.lineSeparator()+
            " %d";

//    @Async
     //  need to consider to put async
    public void sendVerificationCode(String email){
        try {
            Optional<EmailCode>  optionalEmailCode = emailCodeRepository.findById(email);
            int code = generateCode();
            if (optionalEmailCode.isEmpty()) {
                EmailCode emailCode = EmailCode.builder()
                        .email(email)
                        .code(code)
                        .sentTime(LocalDateTime.now())
                        .expirationTime(LocalDateTime.now().plusMinutes(5))
                        .build();
                sendEmail(email, code);
                emailCodeRepository.save(emailCode);
                System.out.println("emailCode = " + emailCode);
                log.info("EmailCode has been sent to %s".formatted(email));
                return;
            }

            EmailCode updatingEmailCode = optionalEmailCode.get();
            long dif;
            if ((dif = ChronoUnit.SECONDS.between(LocalDateTime.now(), updatingEmailCode.getSentTime())) > - 60)
                throw ApiException.throwException("You can resent email code after %s seconds".formatted(Math.abs(60 + dif)));

            updatingEmailCode.setCode(code);
            updatingEmailCode.setSentTime(LocalDateTime.now());
            updatingEmailCode.setExpirationTime(updatingEmailCode.getSentTime().plusMinutes(5));
            sendEmail(email, code);
            emailCodeRepository.save(updatingEmailCode);
            System.out.println("email = " + updatingEmailCode);
            log.info("EmailCode has been sent to %s".formatted(email));
        }
        catch (RedisConnectionException ex){
            log.error("Can not send email",ex);
            throw  new RedisConnectionException(ex.getMessage());
        }

    }

    public boolean verifyEmail(String email, String code){
        EmailCode emailCode = emailCodeRepository.findById(email)
                .orElseThrow(
                        () -> ApiException.throwException("Email not send to %s".formatted(email))
                );
        LocalDateTime expirationTime = emailCode.getExpirationTime();

        if (LocalDateTime.now().isBefore(expirationTime)){
            if (code.equals(String.valueOf(emailCode.getCode()))){
                return true;
            }
            throw ApiException.throwException("Wrong sms code entered");
        }
        throw ApiException.throwException("Code already expired!");
    }

    private void sendEmail(String email, int code){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Gym-Fitness APP");
        message.setText(text.formatted(email,code));
        mailSender.send(message);
    }

    private int generateCode(){
        Random random = new Random();
        return random.nextInt( 100000, 999999 );
    }
}
