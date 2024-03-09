package uz.pdp.gymfitnessapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.gymfitnessapp.entity.Card;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    boolean existsByNumber(String number);
}
