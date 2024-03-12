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
    public Appointment() {

    }

    @Embeddable
    @Getter
    @Setter
    public static class AppointmentId implements Serializable {
        private UUID userId;
        private UUID trainerId;

        public AppointmentId(UUID userId, UUID trainerId) {

        }
        public AppointmentId() {

        }
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
