package uz.pdp.gymfitnessapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.dto.CardCreateDto;
import uz.pdp.gymfitnessapp.dto.CardUpdateDto;
import uz.pdp.gymfitnessapp.dto.trainer.TrainerCreateDTO;
import uz.pdp.gymfitnessapp.dto.trainer.TrainerResponseDTO;
import uz.pdp.gymfitnessapp.dto.trainer.TrainerUpdateDTO;
import uz.pdp.gymfitnessapp.entity.Card;
import uz.pdp.gymfitnessapp.service.TrainerService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + TrainerController.BASE_URl)
@RequiredArgsConstructor
public class TrainerController {
    public static final String BASE_URl = "/trainer";
    private final TrainerService service;

    @PostMapping
    public ApiResponse<TrainerResponseDTO> create(@RequestBody @Valid TrainerCreateDTO createDto) {
        return ApiResponse.body(service.internalCreate(createDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<TrainerResponseDTO> get(@PathVariable UUID id) {
        return ApiResponse.body(service.get(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<TrainerResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid TrainerUpdateDTO updateDto) {
        return ApiResponse.body(service.internalUpdate(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable UUID id ) {
        service.delete(id);
        return ApiResponse.respond(true, "Successfully deleted");
    }
}
