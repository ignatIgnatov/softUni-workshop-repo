package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.BrandEntity;
import com.example.mobilelele.model.entity.ModelEntity;
import com.example.mobilelele.model.view.BrandViewModel;
import com.example.mobilelele.model.view.ModelViewModel;
import com.example.mobilelele.repository.ModelRepo;
import com.example.mobilelele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    private final ModelRepo modelRepo;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(ModelRepo modelRepo, ModelMapper modelMapper) {
        this.modelRepo = modelRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandViewModel> getAllBrands() {
        // List which we keep representation of All Brands View
        List<BrandViewModel> brandViewModelList = new ArrayList<>();
        // get all Models (and Brands) from DB(table:models)
        List<ModelEntity> allModels = modelRepo.findAll();

        allModels.forEach(model -> {
            BrandEntity brand = model.getBrand(); // get Brand for each model
            Optional<BrandViewModel> brandViewModelOptional =
                    findByName(brandViewModelList, brand.getName());
            if (!brandViewModelOptional.isPresent()) { // if NOT Present create new Brand
                BrandViewModel newBrandViewModel = new BrandViewModel();
                modelMapper.map(brand, newBrandViewModel);
                brandViewModelList.add(newBrandViewModel);
                brandViewModelOptional = Optional.of(newBrandViewModel);
            }

            // create new Model and add it to the Brand it belongs
            ModelViewModel newModelViewModel = new ModelViewModel();
            modelMapper.map(model, newModelViewModel);
            brandViewModelOptional.get().addModel(newModelViewModel);
        });

        return brandViewModelList;
    }

    @Override
    public ModelEntity getModelById(Long id) {
        return modelRepo.findById(id).orElse(null);
    }

    private static Optional<BrandViewModel> findByName(List<BrandViewModel> brandViewModel, String name) {
        return brandViewModel.stream()
                .filter(bvm -> bvm.getName().equals(name))
                .findFirst();
    }
}
