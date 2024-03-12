package uz.pdp.gymfitnessapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.config.AuditingConfig;
import uz.pdp.gymfitnessapp.dto.AppointmentDto;
import uz.pdp.gymfitnessapp.entity.Appointment;
import uz.pdp.gymfitnessapp.entity.Card;
import uz.pdp.gymfitnessapp.entity.Trainer;
import uz.pdp.gymfitnessapp.entity.User;
import uz.pdp.gymfitnessapp.repository.AppointmentRepository;
import uz.pdp.gymfitnessapp.repository.TrainerRepository;
import uz.pdp.gymfitnessapp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;
    private final AuditingConfig auditingConfig;

    public AppointmentDto makeAppointment(AppointmentDto dto) {
        // todo estimate appointment fee
        double appointmentFee = 175.99;
        UUID userId = auditingConfig.auditorProvider()
                .getCurrentAuditor()
                .orElseThrow(() -> ApiException.throwException("there is not user in session"));
        Trainer trainer = trainerRepository.findById(dto.getTrainerId())
                .orElseThrow(() -> ApiException.throwException("Trainer not found"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentDate = dto.getDate();

        User user = userRepository.findById(userId).orElseThrow();
        Card card = user.getCards()
                .stream()
                .filter(userCard -> userCard.getId().equals(dto.getCardId()))
                .findFirst()
                .orElseThrow(() -> ApiException.throwException("User card not found"));

        if (appointmentDate.isBefore(now))
            throw ApiException.throwException("Appointment date can't be before " + now);

        if (card.getBalance() < appointmentFee)
            throw ApiException.throwException("there isn't enough money on the card");

        card.setBalance(card.getBalance()-appointmentFee);
        userRepository.save(user);

        Appointment appointment = new Appointment(user, trainer, appointmentDate);
        appointmentRepository.save(appointment);
        return new AppointmentDto(trainer.getId(), appointmentDate, card.getId());
    }
}
