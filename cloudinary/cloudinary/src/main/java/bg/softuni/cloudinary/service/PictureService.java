package bg.softuni.cloudinary.service;

import bg.softuni.cloudinary.model.binding.PictureBindingModel;
import bg.softuni.cloudinary.model.service.PictureServiceModel;
import bg.softuni.cloudinary.model.view.PictureViewModel;
import java.io.IOException;
import java.util.List;

public interface PictureService {

    void addPicture(PictureBindingModel pictureBindingMode) throws IOException;

    List<PictureViewModel> findAll();

    void deletePicture(String publicId);
}
