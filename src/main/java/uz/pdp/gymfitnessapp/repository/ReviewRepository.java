package uz.pdp.gymfitnessapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.pdp.gymfitnessapp.entity.Review;
import uz.pdp.gymfitnessapp.entity.Trainer;
import uz.pdp.gymfitnessapp.entity.User;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID>, JpaSpecificationExecutor<Review> {
    void deleteByUserAndTrainer(User user, Trainer trainer);
}
