package uz.pdp.gymfitnessapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.dto.AppointmentDto;
import uz.pdp.gymfitnessapp.service.AppointmentService;

@RestController
@RequestMapping(AppConstants.BASE_PATH + AppointmentController.BASE_URL)
@RequiredArgsConstructor
public class AppointmentController {
    public static final String BASE_URL = "/appointment";
    private final AppointmentService service;

    @PostMapping
    public ApiResponse<AppointmentDto> makeAppointment(@RequestBody @Valid AppointmentDto appointmentDto) {
        AppointmentDto response = service.makeAppointment(appointmentDto);
        return ApiResponse.body(response);
    }
}
