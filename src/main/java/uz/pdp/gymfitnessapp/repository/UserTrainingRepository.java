package uz.pdp.gymfitnessapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.gymfitnessapp.entity.UserTraining;
import uz.pdp.gymfitnessapp.enums.TrainingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface UserTrainingRepository extends JpaRepository<UserTraining, UUID> {
     List<UserTraining> findAllByStatusEqualsAndDateBetweenAndUser_Id(TrainingStatus status, LocalDateTime date, LocalDateTime date2, UUID user_id);
}
