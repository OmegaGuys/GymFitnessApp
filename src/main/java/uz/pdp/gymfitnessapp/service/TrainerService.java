package uz.pdp.gymfitnessapp.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.dto.trainer.TrainerCreateDTO;
import uz.pdp.gymfitnessapp.dto.trainer.TrainerResponseDTO;
import uz.pdp.gymfitnessapp.dto.trainer.TrainerUpdateDTO;
import uz.pdp.gymfitnessapp.entity.Trainer;
import uz.pdp.gymfitnessapp.mapper.TrainerMapper;
import uz.pdp.gymfitnessapp.repository.TrainerRepository;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class TrainerService extends GenericService<Trainer, UUID, TrainerResponseDTO, TrainerCreateDTO, TrainerUpdateDTO> {
    private final TrainerRepository repository;
    private final TrainerMapper mapper;
    private final Class<Trainer>entityClass = Trainer.class;

    @Override
    public TrainerResponseDTO internalCreate(TrainerCreateDTO trainerCreateDTO) {
        String email = trainerCreateDTO.getEmail();
        if (repository.existsByEmail(email)) {
            throw ApiException.throwException("Email already exists");
        }
        Trainer trainer = mapper.toEntity(trainerCreateDTO);
        trainer.setPassword(trainerCreateDTO.getPassword());
        trainer = repository.save(trainer);
        repository.save(trainer);
        return mapper.toResponseDto(trainer);
    }

    @Override
    public TrainerResponseDTO internalUpdate(UUID id, TrainerUpdateDTO trainerUpdateDTO) {
        Trainer trainer = getById(id);
        mapper.toEntity(trainerUpdateDTO, trainer);
        Trainer updatedTrainer = repository.save(trainer);
        return mapper.toResponseDto(updatedTrainer);
    }

    protected Trainer getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> ApiException.throwException("Trainer with id = %s not found".formatted(id))
                );
    }
}
