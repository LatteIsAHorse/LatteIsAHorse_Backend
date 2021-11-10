package team.latte.LatteIsAHorse.common.util.firebase;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.latte.LatteIsAHorse.config.exception.CustomException;
import team.latte.LatteIsAHorse.config.response.ExceptionStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
@Slf4j
public class FirebaseFileService implements FileService {

    @Value("${firebase.secret}")
    private String secret;

    @Value("${firebase.bucket.name}")
    private String bucket;

    private Storage storage;

    @Value("${firebase.image.pre_uri}")
    private String preUri;

    @Value("${firebase.image.post_uri}")
    private String postUri;

    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            ClassPathResource serviceAccount = new ClassPathResource(secret);
            Credentials credentials = GoogleCredentials.fromStream(serviceAccount.getInputStream());
            storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        } catch (Exception e) {
            throw new CustomException(ExceptionStatus.SERVER_ERROR);
        }
    }

    public String upload(MultipartFile multipartFile) {
        String imageName = null;
        try {
            String fileName = multipartFile.getOriginalFilename(); // 파일 이름
            fileName = generateUniqueFileName(fileName);           // 랜덤 스트링

            File file = convertToFile(multipartFile, fileName);    // multipartFile => File
            imageName = uploadFile(file, fileName);                // 파일 업로드
            file.delete();                                         // 프로젝트 폴더에 저장된 업로드 파일 삭제

            return preUri + imageName + postUri;
        } catch (Exception e) {
            throw new CustomException(ExceptionStatus.SERVER_ERROR);
        }
    }

    private String generateUniqueFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getExtension(fileName));
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobInfo blobInfo = getBlobInfo(fileName);
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return fileName;
    }

    public boolean deleteFile(String fileName) {
        fileName = fileName.replace(preUri,"").replace(postUri,"");
        BlobInfo blobInfo = getBlobInfo(fileName);
        storage.delete(blobInfo.getBlobId());
        return true;
    }

    private BlobInfo getBlobInfo(String fileName) {
        BlobId blobId = BlobId.of(bucket, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("media")
                .build();
        return blobInfo;
    }
}