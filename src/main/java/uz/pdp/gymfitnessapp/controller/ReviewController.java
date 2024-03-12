package uz.pdp.gymfitnessapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.dto.ReviewDto;
import uz.pdp.gymfitnessapp.dto.ReviewResponseDto;
import uz.pdp.gymfitnessapp.service.ReviewService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + ReviewController.BASE_URL)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;
    public static final String BASE_URL = "/review";

    @PostMapping("/{userId}")
    public ApiResponse<?> leave(@PathVariable UUID userId, @RequestBody @Valid ReviewDto reviewDto) {
        service.leave(userId, reviewDto);
        return ApiResponse.respond(true, "Review is successfully left");
    }

    @DeleteMapping
    public ApiResponse<?> delete(@RequestParam UUID userId, @RequestParam UUID trainerId) {
        service.delete(userId, trainerId);
        return ApiResponse.respond(true, "Successfully deleted");
    }

    @GetMapping
    public ApiResponse<Page<ReviewResponseDto>> getAll(@RequestParam String predicate, Pageable pageable) {
        return ApiResponse.body(service.getAll(predicate,pageable));
    }

}
