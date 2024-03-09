package uz.pdp.gymfitnessapp.entity.temp;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class AbsUUIDEntity {
    @Id
    protected UUID id;
}
