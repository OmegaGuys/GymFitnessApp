package uz.pdp.gymfitnessapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import uz.pdp.gymfitnessapp.dto.TrainingDto;
import uz.pdp.gymfitnessapp.entity.temp.AbsUUIDEntity;

@SpringBootApplication
@OpenAPIDefinition
@EnableAsync
public class GymFitnessAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymFitnessAppApplication.class, args);
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration localhost = new RedisStandaloneConfiguration( "localhost", 6379 );
        /*localhost.setUsername("default");
        localhost.setPassword("id4weIPgfqUB8COBcPbz2SNdi6XFSfnO");*/
        return new JedisConnectionFactory( localhost );
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory( jedisConnectionFactory() );
        return template;
    }
  
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
