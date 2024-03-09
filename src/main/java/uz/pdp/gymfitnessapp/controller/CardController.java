package uz.pdp.gymfitnessapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.dto.CardCreateDto;
import uz.pdp.gymfitnessapp.dto.CardUpdateDto;
import uz.pdp.gymfitnessapp.entity.Card;
import uz.pdp.gymfitnessapp.service.CardService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + CardController.BASE_URL)
@RequiredArgsConstructor
public class CardController {
    public static final String BASE_URL = "/card";
    private final CardService service;

    @GetMapping("/{id}")
    public ApiResponse<Card> get(@PathVariable UUID id) {
        return ApiResponse.body(service.get(id));
    }

    @PostMapping
    public ApiResponse<Card> create(@RequestBody @Valid CardCreateDto createDto) {
        return ApiResponse.body(service.create(createDto));
    }

    @PutMapping("/{id}")
    public ApiResponse<Card> update(@PathVariable UUID id, @RequestBody @Valid CardUpdateDto updateDto) {
        return ApiResponse.body(service.update(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable UUID id ) {
        service.delete(id);
        return ApiResponse.respond(true, "Successfully deleted");
    }
}
