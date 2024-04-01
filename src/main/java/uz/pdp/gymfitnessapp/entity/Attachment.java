package uz.pdp.gymfitnessapp.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Attachment extends AbsUUIDEntity {
    private String name;
    private String originalName;
    private String contentType;
    private long size;

    public Attachment(UUID id, String name, String originalName, String contentType, long size) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.contentType = contentType;
        this.size = size;
    }
}
