package uz.pdp.gymfitnessapp.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.pdp.gymfitnessapp.dto.trainer.TrainerCreateDTO;
import uz.pdp.gymfitnessapp.dto.trainer.TrainerResponseDTO;
import uz.pdp.gymfitnessapp.dto.trainer.TrainerUpdateDTO;
import uz.pdp.gymfitnessapp.entity.Trainer;

@Component
@RequiredArgsConstructor
public class TrainerMapper extends GenericMapper<Trainer, TrainerCreateDTO, TrainerResponseDTO, TrainerUpdateDTO>{

    private final ModelMapper mapper;
    @Override
    public Trainer toEntity(TrainerCreateDTO trainerCreateDTO) {
        return mapper.map(trainerCreateDTO, Trainer.class);
    }

    @Override
    public TrainerResponseDTO toResponseDto(Trainer trainer) {
        return mapper.map(trainer, TrainerResponseDTO.class);
    }

    @Override
    public void toEntity(TrainerUpdateDTO trainerUpdateDTO, Trainer trainer) {
        mapper.map(trainerUpdateDTO, trainer);
    }
}
