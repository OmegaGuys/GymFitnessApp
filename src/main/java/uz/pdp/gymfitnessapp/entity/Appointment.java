package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Appointment {
    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AppointmentId implements Serializable {
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

    public Appointment(User user, Trainer trainer, LocalDateTime date) {
        this.id = new AppointmentId(user.getId(),trainer.getId());
        this.user = user;
        this.trainer = trainer;
        this.date = date;
    }
}
