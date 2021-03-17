package com.github.kacperkwiatkowski.holidayscheduler_backend.image;

import com.github.kacperkwiatkowski.holidayscheduler_backend.buckets.FileStore;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Slf4j
@Service
public class ImageService {

    private final UserFacade userFacade;
    private final FileStore fileStore;

    ImageService(UserFacade userFacade, FileStore fileStore) {
        this.userFacade = userFacade;

        this.fileStore = fileStore;
    }

//    public void uploadUserImage(int id, MultipartFile file){
//        Optional<UserDto> foundUser = Optional.ofNullable(userService.findById(id));
//        if(foundUser.isEmpty()){
//            throw ObjectNotFoundException.createWith("Id does not exist.");
//        }
//
//        isFileEmpty(file);
//        isImage(file);
//        Map<String, String> metadata = extractMetadata(file);
//        uploadImage(file, metadata, foundUser.get());
//    }

//    public byte[] downloadUserImage(int id){
//        Optional<UserDto> foundUser = Optional.ofNullable(userService.findById(id));
//
//        if(foundUser.isEmpty()){
//            throw ObjectNotFoundException.createWith("Id does not exist.");
//        }
//
//        UserDto user = foundUser.get();
//
//        String path = String.format("%s/%s",
//                BucketName.USER_IMAGE.getBucketName(),
//                user.getId());
//
//        String defaultPath = String.format("%s/%s",
//                BucketName.USER_IMAGE.getBucketName(),
//                "default");
//
//        return userService.getUserOptionalOfUserImageUrl(user.getId())
//                .map(key ->
//                        fileStore.download(path, key))
//                .orElse(
//                        fileStore.download(defaultPath, "default.jpg"));
//    }

//    private void uploadImage(MultipartFile file, Map<String, String> metadata, UserDto user) {
//        String path = String.format("%s/%s", BucketName.USER_IMAGE.getBucketName(), user.getId());
//        String filename = String.format("%s-%s", "profile", "image");
//        user.setImageUrl(filename);
//        userService.save(user);
//        try {
//            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType()
        )) {
            throw new IllegalStateException("File must be an image! [" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot upload an empty file!");
        }
    }
}
