package uz.pdp.gymfitnessapp.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.gymfitnessapp.entity.Attachment;
import uz.pdp.gymfitnessapp.entity.Card;
import uz.pdp.gymfitnessapp.entity.Notification;
import uz.pdp.gymfitnessapp.entity.Subscription;
import uz.pdp.gymfitnessapp.entity.enums.FitnessLevel;
import uz.pdp.gymfitnessapp.entity.enums.Gender;
import uz.pdp.gymfitnessapp.entity.enums.Goal;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private String fullName;
    private String email;
    private Gender gender;
    private int weight;
    private int height;
    private FitnessLevel fitnessLvl;
    private Goal goal;
    private LocalDate registered;
    private Attachment image;
    private List<Card> cards;
    private List<Notification> notifications;
    private Subscription subscription;
    private LocalDate subscribeDate;
    private String token;
}
