package com.github.kacperkwiatkowski.holidayscheduler_backend.buckets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {

    USER_IMAGE("holidays-scheduler-images");

    private String bucketName;

}
