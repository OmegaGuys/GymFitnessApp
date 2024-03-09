package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Attachment extends AbsUUIDEntity {
    private String name;
    private String originalName;
    private String contentType;
    private long size;
}
