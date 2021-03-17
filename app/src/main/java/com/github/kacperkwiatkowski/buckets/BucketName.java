package com.github.kacperkwiatkowski.buckets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {

    USER_IMAGE("holidays-scheduler-images");

    private String bucketName;

}
