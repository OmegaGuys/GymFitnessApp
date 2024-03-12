package uz.pdp.gymfitnessapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    @JsonProperty("trainer_id")
    private UUID trainerId;

    private LocalDateTime date;

    @JsonProperty("payment_card_id")
    private UUID cardId;
}
