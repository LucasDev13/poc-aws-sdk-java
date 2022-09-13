package com.pocawssdkjava.controllers;

import com.pocawssdkjava.usecases.DownloadFileBucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "api/")
public class DownloadFileBucketController {

    @Autowired
    private DownloadFileBucket s3;

    @GetMapping(value = "/s3/{keyObject}")
    public ResponseEntity<?> getObjectBucket(@PathVariable String keyObject) throws IOException {
        log.info("Inicio do controller...");
        s3.downloadObject(keyObject);
        return ResponseEntity.ok().build();
    }
}
