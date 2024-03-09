package uz.pdp.gymfitnessapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.dto.TrainingDto;
import uz.pdp.gymfitnessapp.service.TrainingService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + TrainingController.BASE_URL)
@RequiredArgsConstructor
public class TrainingController {
    public static final String BASE_URL = "/training";
    private final TrainingService trainingService;

    @GetMapping("/all")
    public ApiResponse<Page<TrainingDto>> getAll(Pageable pageable,
                                                 @RequestParam(required = false)
                                           String predicate) {
        return ApiResponse.body(trainingService.getAll(predicate,pageable));
    }

    @PostMapping
    public ApiResponse<TrainingDto> create(@RequestBody @Valid TrainingDto trainingDto) {
        return ApiResponse.body(trainingService.create(trainingDto));
    }

    @PutMapping("/{id}")
    public ApiResponse<TrainingDto> update(@PathVariable UUID id, @RequestBody @Valid TrainingDto trainingDto) {
        return ApiResponse.body(trainingService.update(id, trainingDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable UUID id) {
        trainingService.delete(id);
        return ApiResponse.respond(true, "Successfully deleted");
    }


}
