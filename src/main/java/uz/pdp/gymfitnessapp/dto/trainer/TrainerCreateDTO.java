package uz.pdp.gymfitnessapp.dto.trainer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.gymfitnessapp.entity.enums.TrainingType;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerCreateDTO {

    @NotNull
    private String fullName;

    @Email(message = "invalid email")
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private TrainingType trainingType;

    private int yearsOfExperience;

}
