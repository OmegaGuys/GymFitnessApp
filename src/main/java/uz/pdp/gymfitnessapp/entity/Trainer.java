package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.gymfitnessapp.entity.enums.TrainingType;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Trainer extends AbsUUIDEntity {
    private String fullName;

    @Enumerated(EnumType.STRING)
    private TrainingType trainingType;

    private int yearsOfExperience;

    private double rating;

    private int completed;

    @OneToMany
    private List<User> activeClients;

    @ManyToOne
    private Attachment image;
}
