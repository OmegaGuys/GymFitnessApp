package uz.pdp.gymfitnessapp.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.dto.TrainingDto;
import uz.pdp.gymfitnessapp.entity.Trainer;
import uz.pdp.gymfitnessapp.entity.Training;
import uz.pdp.gymfitnessapp.mapper.TrainingMapper;
import uz.pdp.gymfitnessapp.repository.TrainerRepository;
import uz.pdp.gymfitnessapp.repository.TrainingRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class TrainingService extends GenericService<Training, UUID, TrainingDto, TrainingDto, TrainingDto> {

    private final TrainingRepository repository;
    private final TrainerRepository trainerRepository;
    private final Class<Training> entityClass = Training.class;
    private final TrainingMapper mapper;


    @Override
    protected TrainingDto internalCreate(TrainingDto trainingDto) {
        Training training = mapper.toEntity(trainingDto);
        if (trainingDto.getParentId() != null) {
            Optional<Training> parent =
                    repository.findById(trainingDto.getParentId());
            if (parent.isPresent()) {
                training.setParent(parent.get());
            } else throw ApiException.throwException("parent category not found");
        }
        if (trainingDto.getTrainerId() != null) {
            Optional<Trainer> trainner =
                    trainerRepository.findById(trainingDto.getTrainerId());
            if (trainner.isPresent()) {
                training.setTrainer(trainner.get());
            } else throw ApiException.throwException("trainer not found");
        }
        repository.save(training);
        return trainingDto;
    }

    @Override
    protected TrainingDto internalUpdate(UUID uuid, TrainingDto trainingDto) {
        Optional<Training> training = repository.findById(uuid);
        if (training.isPresent()) {
            Training training1 = training.get();
            if (trainingDto.getParentId() != null) {
                Optional<Training> parent =
                        repository.findById(trainingDto.getParentId());
                if (parent.isPresent()) {
                    training1.setParent(parent.get());
                } else throw ApiException.throwException("parent category not found");
            }
            if (trainingDto.getTrainerId() != null) {
                Optional<Trainer> trainer =
                        trainerRepository.findById(trainingDto.getTrainerId());
                if (trainer.isPresent()) {
                    training1.setTrainer(trainer.get());
                } else throw ApiException.throwException("trainer not found");
            }
            mapper.toEntity(trainingDto, training1);
            repository.save(training1);

        }else throw ApiException.throwException("training not found");
        return trainingDto;
    }


}
