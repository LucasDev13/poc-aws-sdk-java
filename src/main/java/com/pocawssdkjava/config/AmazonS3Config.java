package com.pocawssdkjava.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AmazonS3Config {

    @Value("${cloud.aws.authentication.aws-access-key-id}")
    private String ACCESS_KEY;

    @Value("${cloud.aws.authentication.aws-secret-access-key}")
    private String SECRET_KEY;

    @Value("${localstack.s3-endpoint}")
    private String localStackBucketUrl;

    @Value("cloud.aws.region.static")
    private String region;

    @Bean(name = "amazonS3")
    public AmazonS3 amazonS3(){
        log.info("Autenticando S3...");
        if(localStackBucketUrl != null && !localStackBucketUrl.isEmpty()){
            return AmazonS3ClientBuilder
                    .standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(localStackBucketUrl, region))
                    .build();
        }

        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .build();
    }
}
