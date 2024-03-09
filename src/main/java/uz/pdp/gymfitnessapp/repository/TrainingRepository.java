package uz.pdp.gymfitnessapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.gymfitnessapp.dto.TrainingDto;
import uz.pdp.gymfitnessapp.entity.Training;
import uz.pdp.gymfitnessapp.entity.enums.Category;


import java.util.UUID;

@Repository
public interface TrainingRepository extends GenericRepository<Training, UUID> {
//    Training findAllByCategory(Category category);
}
