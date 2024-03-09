package uz.pdp.gymfitnessapp.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.gymfitnessapp.entity.Trainer;

import java.util.UUID;
@Repository
public interface TrainerRepository extends GenericRepository<Trainer, UUID>{
    boolean existsByEmail(String email);
}
