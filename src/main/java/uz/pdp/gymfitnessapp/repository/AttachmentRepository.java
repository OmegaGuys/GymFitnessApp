package uz.pdp.gymfitnessapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.gymfitnessapp.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
