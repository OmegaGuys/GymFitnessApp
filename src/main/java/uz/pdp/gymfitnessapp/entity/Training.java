package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.gymfitnessapp.entity.enums.Category;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Training extends AbsUUIDEntity {
    @Column(nullable = false)
    private String name;

    private String description;

    private int duration;

    private int calories;

    @ManyToOne
    private Training parent;

    @Enumerated(EnumType.STRING)
    private Category category;

    private boolean isPremium;

    @OneToOne
    private Attachment video;
}
