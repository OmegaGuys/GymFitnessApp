package uz.pdp.gymfitnessapp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.gymfitnessapp.entity.enums.FitnessLevel;
import uz.pdp.gymfitnessapp.entity.enums.Goal;


@Getter
@Setter
@ToString
public class SignUpDto {

    @NotEmpty
    private String fullName;

    @Email(message = "invalid email")
    private String email;

    @Size(min = 6, max = 18, message = "Password must be between {min} and {max}")
    private String password;

    @Size(min = 6, max = 18, message = "Password must be between {min} and {max}")
    private String confirmPassword;

//    @NotNull
    private FitnessLevel fitnessLvl;

//    @NotNull
    private Goal goal;

}
