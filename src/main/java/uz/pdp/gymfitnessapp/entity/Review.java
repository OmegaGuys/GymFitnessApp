package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import uz.pdp.gymfitnessapp.entity.enums.ReviewType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Review {
    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class ReviewId implements Serializable {
        private UUID userId;
        private UUID trainerId;
    }

    @EmbeddedId
    private ReviewId id;
    @MapsId("userId")
    @OneToOne
    private User user;

    @MapsId("trainerId")
    @ManyToOne
    private Trainer trainer;

    private String comment;

    private double rate;

    @Enumerated(EnumType.STRING)
    private ReviewType type;

    @CreatedDate
    private LocalDateTime leftDate;

    public void setId(User user, Trainer trainer) {
        this.id = new ReviewId(user.getId(), trainer.getId());
    }
}
