package uz.pdp.gymfitnessapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.dto.InsightDTO;
import uz.pdp.gymfitnessapp.entity.UserTraining;
import uz.pdp.gymfitnessapp.enums.TrainingStatus;
import uz.pdp.gymfitnessapp.repository.UserTrainingRepository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsightService {
    private final UserTrainingRepository repository;

    public InsightDTO getInsightByDay(int day, int month, int year) {
        LocalDate localDate;
        try {
            localDate = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw ApiException.throwException("date unsupported");
        }
        List<UserTraining> userTrainings = repository.findAllByStatusEqualsAndDateBetweenAndUser_Id(
                TrainingStatus.FINISHED,
                LocalDateTime.of(localDate, LocalTime.MIN),
                LocalDateTime.of(localDate, LocalTime.MAX),
                UUID.fromString("be8f7db5-ae43-427d-9d6b-f5563b7158b2")); // TODO: 11/03/2024 get current user's id
        int activeCalories = 0, time = 0;
        for (UserTraining userTraining : userTrainings) {
            activeCalories += userTraining.getTraining().getCalories();
            time += userTraining.getTraining().getDuration();
        }
        return new InsightDTO(activeCalories,time);
    }
}
