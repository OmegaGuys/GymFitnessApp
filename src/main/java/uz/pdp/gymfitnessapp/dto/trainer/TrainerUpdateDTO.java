package uz.pdp.gymfitnessapp.dto.trainer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.gymfitnessapp.entity.enums.TrainingType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerUpdateDTO{

    @NotNull
    private String fullName;

    @NotNull
    private TrainingType trainingType;

    private  int yearsOfExperience;

}
