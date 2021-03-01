package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(
            path = "/{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserImage(
            @PathVariable("id") int id,
            @RequestParam("file") MultipartFile file) {
        imageService.uploadUserImage(id, file);
    }

    @GetMapping( "/{id}/image/download")
    public void downloadUserImage(
            @PathVariable("id") int id) {
        imageService.downloadUserImage(id);
    }
}
