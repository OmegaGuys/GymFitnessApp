package uz.pdp.gymfitnessapp.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.gymfitnessapp.entity.Appointment;

import java.util.UUID;

@Repository
public interface AppointmentRepository extends GenericRepository<Appointment, UUID>{

}
