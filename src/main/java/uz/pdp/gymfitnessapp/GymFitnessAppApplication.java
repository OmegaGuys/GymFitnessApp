package uz.pdp.gymfitnessapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.pdp.gymfitnessapp.dto.TrainingDto;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;

@SpringBootApplication
@OpenAPIDefinition
public class GymFitnessAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymFitnessAppApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
