package uz.pdp.gymfitnessapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.gymfitnessapp.entity.EmailCode;

import java.util.Optional;

@Repository
public interface EmailCodeRepository extends CrudRepository<EmailCode, String> {
    Optional<EmailCode> findByEmail(String email);

}
