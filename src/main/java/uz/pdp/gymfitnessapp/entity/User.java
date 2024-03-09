package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import uz.pdp.gymfitnessapp.entity.enums.FitnessLevel;
import uz.pdp.gymfitnessapp.entity.enums.Gender;
import uz.pdp.gymfitnessapp.entity.enums.Goal;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
@DynamicUpdate
public class User extends AbsUUIDEntity {
    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int weight;

    private int height;

    @Enumerated(EnumType.STRING)
    private FitnessLevel fitnessLvl;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    @CreatedDate
    private LocalDate registered;

    @ManyToOne
    private Attachment image;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Card> cards = new ArrayList<>();

    @ManyToMany
    private List<Notification> notifications;

    @ManyToOne
    private Subscription subscription;

    private LocalDate subscribeDate;
}
