package com.example.StudyJava.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class S3Service {

//    @Autowired
//    private AmazonS3 amazonS3;

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    // Convert MultipartFile to File
    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    // Upload file to S3
//    public void uploadFile(String bucketName, MultipartFile multipartFile) {
//        try {
//            File file = convertMultiPartFileToFile(multipartFile);
//            amazonS3.putObject(new PutObjectRequest(bucketName, generateFileName(multipartFile), file)
//                    .withCannedAcl(CannedAccessControlList.PublicRead));
//            file.delete(); // Delete the temporary file after upload
//        } catch (IOException e) {
//            throw new RuntimeException("File conversion failed", e);
//        }
//    }
}
