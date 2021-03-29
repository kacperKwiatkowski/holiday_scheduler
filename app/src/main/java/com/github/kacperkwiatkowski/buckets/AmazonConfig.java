package com.github.kacperkwiatkowski.buckets;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.github.kacperkwiatkowski.user.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
class AmazonConfig {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    AmazonConfig(UserFacade userFacade, PasswordEncoder passwordEncoder) {
        this.userFacade = userFacade;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    AmazonS3 s3() {

        Properties awsKeys = getProperties();

        AWSCredentials awsCredentials = new BasicAWSCredentials(awsKeys.getProperty("idKey"), awsKeys.getProperty("secretKey"));

        return AmazonS3Client.builder()
                .withRegion("eu-central-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    private Properties getProperties() {
//        userFacade.save(new UserSecurityDto("ola@gmail.com", passwordEncoder.encode("1234"), "Ola", "Olska", RoleType.ADMIN, 26, "profile-image"));

        Properties awsKeys = new Properties();
        try {
            awsKeys.load(new FileInputStream("/home/scypion/Programing/Java_Projects/My_Projects/holiday_scheduler/adapters/src/main/resources/awsKeys.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return awsKeys;
    }
}
