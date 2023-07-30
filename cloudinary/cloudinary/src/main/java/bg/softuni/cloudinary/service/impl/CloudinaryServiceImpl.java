package bg.softuni.cloudinary.service.impl;

import bg.softuni.cloudinary.repository.PictureRepository;
import bg.softuni.cloudinary.service.CloudinaryImage;
import bg.softuni.cloudinary.service.CloudinaryService;
import com.cloudinary.Cloudinary;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    private final Cloudinary cloudinary;
    private final PictureRepository pictureRepository;

    public CloudinaryServiceImpl(Cloudinary cloudinary, PictureRepository pictureRepository) {
        this.cloudinary = cloudinary;
        this.pictureRepository = pictureRepository;
    }


    @Override
    public CloudinaryImage uploadImage(MultipartFile multipartFile) throws IOException {

        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        @SuppressWarnings("unchecked")
        Map<String, String> uploadResult = cloudinary
                .uploader()
                .upload(tempFile, Map.of());

        try {
            String url = uploadResult.getOrDefault(URL, "https://img.freepik.com/free-vector/oops-404-error-with-broken-robot-concept-illustration_114360-5529.jpg?w=740&t=st=1690554644~exp=1690555244~hmac=0549e62c8913ffab1f53d58e4fb87d5653e1ad5a024eede48195a15e428fc3b3");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            return new CloudinaryImage().setPublicId(publicId).setUrl(url);
        } finally {
            tempFile.delete();
        }
    }

    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
