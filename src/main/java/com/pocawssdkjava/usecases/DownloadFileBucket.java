package com.pocawssdkjava.usecases;

import com.amazonaws.services.s3.AmazonS3;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Slf4j
public class DownloadFileBucket {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3-bucket}")
    private String bucket_name;

    private static final String pathFile = "/home/lucas/Documentos/arquivos/arquivo-exemplo.csv";

    public void downloadObject(String objectName) throws IOException {
        log.info("Inicio");
        var objectInputStream = amazonS3.getObject(bucket_name, objectName).getObjectContent();
        log.info("Download ...");
        try {
            FileUtils.copyInputStreamToFile(objectInputStream,
                    new File("/home/lucas/Documentos/arquivos/teste-pasta" + File.separator + objectName));
            log.info("Salvou o arquivo " + objectName + " na pasta ...");
        } catch (IOException e) {
            log.info("não deu certo... " + e.getMessage());
        }
    }

    public void uploadingFileToS3(String objectName){
        amazonS3.putObject(bucket_name, objectName,new File(pathFile));
        log.info("Arquivo " + objectName + " foi enviado para o s3");
    }
}
