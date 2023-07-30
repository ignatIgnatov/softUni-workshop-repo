package bg.softuni.cloudinary.service.impl;

import bg.softuni.cloudinary.model.binding.PictureBindingModel;
import bg.softuni.cloudinary.model.entity.PictureEntity;
import bg.softuni.cloudinary.model.view.PictureViewModel;
import bg.softuni.cloudinary.repository.PictureRepository;
import bg.softuni.cloudinary.service.CloudinaryImage;
import bg.softuni.cloudinary.service.CloudinaryService;
import bg.softuni.cloudinary.service.PictureService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    public PictureServiceImpl(CloudinaryService cloudinaryService,
                              PictureRepository pictureRepository,
                              ModelMapper modelMapper) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addPicture(PictureBindingModel pictureBindingModel) throws IOException {
        var picture = createPictureEntity(pictureBindingModel.getPicture(), pictureBindingModel.getTitle());

        pictureRepository.save(picture);
    }

    private PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.uploadImage(file);
        return new PictureEntity()
                .setPublicId(uploaded.getPublicId())
                .setTitle(title)
                .setImgUrl(uploaded.getUrl());
    }

    @Override
    public List<PictureViewModel> findAll() {
        return pictureRepository.
                findAll().
                stream().
                map(pic -> modelMapper.map(pic, PictureViewModel.class)).
                collect(Collectors.toList());
    }

    @Override
    public void deletePicture(String publicId) {
        if (cloudinaryService.delete(publicId)) {
            pictureRepository.deleteAllByPublicId(publicId);
        }
    }
}
