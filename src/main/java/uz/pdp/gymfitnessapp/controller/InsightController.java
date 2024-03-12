package uz.pdp.gymfitnessapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.service.InsightService;

@RestController
@RequestMapping(AppConstants.BASE_PATH +InsightController.BASE_URL)
@RequiredArgsConstructor
public class InsightController {
    public static final String BASE_URL = "/insight";

    public final InsightService service;

    @GetMapping
    public ApiResponse<?> insight(@RequestParam int day,
                                  @RequestParam int month,
                                  @RequestParam int year ){
        return ApiResponse.body(service.getInsightByDay(day,month,year));
    }
}
