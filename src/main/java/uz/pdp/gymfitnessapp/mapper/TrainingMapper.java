package uz.pdp.gymfitnessapp.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.pdp.gymfitnessapp.dto.TrainingDto;
import uz.pdp.gymfitnessapp.entity.Training;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TrainingMapper extends GenericMapper<Training, TrainingDto, TrainingDto,TrainingDto> {
    private final ModelMapper mapper;
    @Override
    public Training toEntity(TrainingDto trainingDto) {
        Training training = mapper.map(trainingDto, Training.class);
        training.setId(UUID.randomUUID());
        return training;
    }

    @Override
    public TrainingDto toResponseDto(Training training) {
        return mapper.map(training, TrainingDto.class);
    }

    @Override
    public void toEntity(TrainingDto trainingDto, Training training) {
     mapper.map(trainingDto,training);
    }
}
