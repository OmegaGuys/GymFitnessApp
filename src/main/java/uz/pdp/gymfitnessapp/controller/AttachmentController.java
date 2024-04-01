package uz.pdp.gymfitnessapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.gymfitnessapp.common.ApiResponse;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.entity.Attachment;
import uz.pdp.gymfitnessapp.service.AttachmentService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + AttachmentController.BASE_URL)
@RequiredArgsConstructor
public class AttachmentController {
    public static final String BASE_URL = "/image";
    private final AttachmentService service;

    @PostMapping
    public ApiResponse<Attachment> upload(@RequestPart MultipartFile file) throws IOException {
        return ApiResponse.body(service.upload(file));
    }

    @GetMapping("/{imageId}")
    public byte[] retrieve(@PathVariable UUID imageId) {
        return service.retrieve(imageId);
    }
}
