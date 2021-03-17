package com.github.kacperkwiatkowski.image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/images")
class ImageController {

    private final ImageService imageService;

    ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
//
//    @PostMapping(
//            path = "/{id}/image/upload",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    void uploadUserImage(
//            @PathVariable("id") int id,
//            @RequestParam("file") MultipartFile file) {
//        imageService.uploadUserImage(id, file);
//    }
//
//    @GetMapping( "/{id}/image/download")
//    ResponseEntity<byte[]>  downloadUserImage(
//            @PathVariable("id") int id) {
//        return ResponseEntity.ok(imageService.downloadUserImage(id));
//    }
}
