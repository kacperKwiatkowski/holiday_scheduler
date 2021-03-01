package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.buckets.BucketName;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.filestore.FileStore;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.UserMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Slf4j
@Service
public class ImageService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileStore fileStore;

    public ImageService(UserRepository userRepository, UserMapper userMapper, FileStore fileStore) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.fileStore = fileStore;
    }

    public void uploadUserImage(int id, MultipartFile file){
        Optional<User> foundUser = Optional.ofNullable(userRepository.findById(id));
        if(foundUser.isEmpty()){
            throw ObjectNotFoundException.createWith("Id does not exist.");
        }

        isFileEmpty(file);
        isImage(file);
        Map<String, String> metadata = extractMetadata(file);
        uploadImage(file, metadata, foundUser.get());
    }

    public byte[] downloadUserImage(int id){
        Optional<User> foundUser = Optional.ofNullable(userRepository.findById(id));

        if(foundUser.isEmpty()){
            throw ObjectNotFoundException.createWith("Id does not exist.");
        }

        User user = foundUser.get();

        String path = String.format("%s/%s",
                BucketName.USER_IMAGE.getBucketName(),
                user.getId());

        return user.getUserOptionalOfUserImageUrl()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    private void uploadImage(MultipartFile file, Map<String, String> metadata, User user) {
        String path = String.format("%s/%s", BucketName.USER_IMAGE.getBucketName(), user.getId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        user.setImageUrl(filename);
        userRepository.save(user);
        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

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
