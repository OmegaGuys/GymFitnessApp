package uz.pdp.gymfitnessapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.gymfitnessapp.entity.enums.Category;

import java.util.UUID;
@Data
public class TrainingDto {
    @NotNull
    private String name;
    private String description;
    private int duration;
    private int calories;
    private UUID parentId;
    private Category category;
    private UUID trainerId;
}
/*
{
"name":"",
"description":"",
"duration":"",
"calories":"",
"parent":"",
"category":"",
"trainerId":""
}
*
*/