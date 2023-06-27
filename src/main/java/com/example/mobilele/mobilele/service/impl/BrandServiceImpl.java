package com.example.mobilele.mobilele.service.impl;

import com.example.mobilele.mobilele.model.entities.BrandEntity;
import com.example.mobilele.mobilele.model.entities.ModelEntity;
import com.example.mobilele.mobilele.model.view.BrandViewModel;
import com.example.mobilele.mobilele.model.view.ModelViewModel;
import com.example.mobilele.mobilele.repository.ModelRepository;
import com.example.mobilele.mobilele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandViewModel> getAllBrands() {
        List<ModelEntity> allModels = modelRepository.findAll();
        List<BrandViewModel> result = new ArrayList<>();

        allModels.forEach(me -> {
            BrandEntity brandEntity = me.getBrand();

            Optional<BrandViewModel> brandViewModelOpt = findByName(result, brandEntity.getName());

            if (!brandViewModelOpt.isPresent()) {
                BrandViewModel newBrandViewModel = new BrandViewModel();
                modelMapper.map(brandEntity, newBrandViewModel);
                result.add(newBrandViewModel);
                brandViewModelOpt = Optional.of(newBrandViewModel);
            }

            ModelViewModel newModelViewModel = new ModelViewModel();
            modelMapper.map(me, newModelViewModel);
            brandViewModelOpt.get().addModel(newModelViewModel);
        });

        return result;
    }

    private static Optional<BrandViewModel> findByName(List<BrandViewModel> allModels, String name) {
       return allModels
               .stream()
               .filter(m -> m.getName().equals(name))
               .findAny();
    }
}
