package bg.softuni.cloudinary.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    CloudinaryImage uploadImage(MultipartFile multipartFile) throws IOException;

    boolean delete(String publicId);
}
