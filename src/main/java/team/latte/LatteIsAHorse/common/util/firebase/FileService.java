package team.latte.LatteIsAHorse.common.util.firebase;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String upload(MultipartFile multipartFile);

}
