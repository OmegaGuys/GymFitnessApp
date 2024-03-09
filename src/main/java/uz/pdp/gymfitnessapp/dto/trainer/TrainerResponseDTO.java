package uz.pdp.gymfitnessapp.dto.trainer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.pdp.gymfitnessapp.entity.enums.TrainingType;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainerResponseDTO{

    UUID id;

    String fullName;

    String email;

    TrainingType trainingType;

    int yearsOfExperience;

    double rating;

    int completed;
}
