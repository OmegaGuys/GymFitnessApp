package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Notification extends AbsUUIDEntity {
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String title;

    private String text;

    @CreatedDate
    private LocalDateTime leftDate;
    private boolean isAboutEvent;
}
