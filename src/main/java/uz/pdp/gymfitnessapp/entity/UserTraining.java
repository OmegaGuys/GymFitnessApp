package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;
import uz.pdp.gymfitnessapp.enums.TrainingStatus;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class UserTraining extends AbsUUIDEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Training training;

    @CreatedDate
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TrainingStatus status;
}
