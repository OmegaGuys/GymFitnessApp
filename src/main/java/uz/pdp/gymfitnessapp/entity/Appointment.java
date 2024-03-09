package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Appointment {
    @Embeddable
    @Getter
    @Setter
    private static class AppointmentId implements Serializable {
        private UUID userId;
        private UUID trainerId;
    }

    @EmbeddedId
    private AppointmentId id;

    @MapsId("userId")
    @OneToOne
    private User user;

    @MapsId("trainerId")
    @ManyToOne
    private Trainer trainer;

    private LocalDateTime date;
}
