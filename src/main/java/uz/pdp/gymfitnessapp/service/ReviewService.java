package uz.pdp.gymfitnessapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.dto.ReviewDto;
import uz.pdp.gymfitnessapp.dto.ReviewResponseDto;
import uz.pdp.gymfitnessapp.entity.Review;
import uz.pdp.gymfitnessapp.entity.Trainer;
import uz.pdp.gymfitnessapp.entity.User;
import uz.pdp.gymfitnessapp.entity.enums.ReviewType;
import uz.pdp.gymfitnessapp.repository.ReviewRepository;
import uz.pdp.gymfitnessapp.repository.UserRepository;
import uz.pdp.gymfitnessapp.rsql.SpecificationBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final TrainerService trainerService;
    private final ModelMapper mapper;

    @Transactional
    public void leave(UUID userId, ReviewDto reviewDto) {
        Trainer trainer = trainerService.getById(reviewDto.getTrainerId());
        User user = userRepository.findById(userId).orElseThrow();
        Review review = mapper.map(reviewDto, Review.class);

        review.setTrainer(trainer);
        review.setLeftDate(LocalDateTime.now());
        review.setUser(user);
        review.setType(getType(reviewDto.getReviewType()));
        review.setId(user, trainer);
        repository.save(review);
    }


    @Transactional
    public void delete(UUID userId, UUID trainerId) {
        User user = userRepository.findById(userId).orElseThrow(() -> ApiException.throwException("User not found"));
        Trainer trainer = trainerService.getById(trainerId);
        repository.deleteByUserAndTrainer(user, trainer);
    }

    @Transactional
    public Page<ReviewResponseDto> getAll(String predicate, Pageable pageable) {
        Specification<Review> specification = SpecificationBuilder.build(predicate);
        Page<Review> page;
        if (specification == null) {
            page = repository.findAll(pageable);
        } else {
            page = repository.findAll(specification, pageable);
        }

        return page.map(entity -> mapper.map(entity, ReviewResponseDto.class));
    }

    private ReviewType getType(String type) {
        try {
            return ReviewType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw ApiException.throwException("Wrong type of review");
        }
    }
}
