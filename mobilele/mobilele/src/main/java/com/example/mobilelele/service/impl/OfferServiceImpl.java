package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.OfferEntity;
import com.example.mobilelele.model.entity.UserEntity;
import com.example.mobilelele.model.entity.UserRoleEntity;
import com.example.mobilelele.model.entity.enums.EngineEnum;
import com.example.mobilelele.model.entity.enums.TransmissionEnum;
import com.example.mobilelele.model.entity.enums.UserRoleEnum;
import com.example.mobilelele.model.service.OfferServiceModel;
import com.example.mobilelele.model.view.AddOfferViewModel;
import com.example.mobilelele.model.view.OfferSummaryViewModel;
import com.example.mobilelele.repository.OfferRepo;
import com.example.mobilelele.repository.UserRepo;
import com.example.mobilelele.service.BrandService;
import com.example.mobilelele.service.OfferService;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    private final UserService userService;
    private final OfferRepo offerRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    public OfferServiceImpl(UserService userService, OfferRepo offerRepo, UserRepo userRepo, ModelMapper modelMapper, BrandService brandService) {
        this.userService = userService;
        this.offerRepo = offerRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
    }

    @Override
    public List<OfferSummaryViewModel> getAllOffers() {
        //TODO - implement mapping
        List<OfferEntity> allOffersList = offerRepo.findAll();
        List<OfferSummaryViewModel> offerSummaryViewModel = new ArrayList<>();
        allOffersList.forEach(offer -> {
            OfferSummaryViewModel summaryViewModel = new OfferSummaryViewModel();
            modelMapper.map(offer, summaryViewModel);
            offerSummaryViewModel.add(summaryViewModel);
        });

        return offerSummaryViewModel;
//        throw new UnsupportedOperationException("Coming soon...");
    }

    @Override
    public Long saveOffer(AddOfferViewModel addOfferViewModel, String username) {
        OfferEntity offer = modelMapper.map(addOfferViewModel, OfferEntity.class);
        offer.setModel(brandService.getModelById(Long.parseLong(addOfferViewModel.getModel())));
        offer.setEngine(EngineEnum.valueOf(addOfferViewModel.getEngine()));
        offer.setTransmission(TransmissionEnum.valueOf(addOfferViewModel.getTransmission()));
        offer.setSeller(userService.getUserByName(username));
        OfferEntity currentOffer = offerRepo.save(offer);
        return currentOffer.getId();
    }

    @Override
    public OfferServiceModel findOfferById(Long id) {
        return modelMapper.map(
                offerRepo.findById(id).orElse(null), OfferServiceModel.class);
    }

    @Override
    public void delete(Long id) {
        offerRepo.deleteById(id);
    }

    @Override
    public boolean isOwner(String username, Long id) {
       Optional<OfferEntity> offerOpt = offerRepo.findById(id);

       Optional<UserEntity> caller = userRepo.findByUsername(username);

       if (offerOpt.isEmpty() || caller.isEmpty()) {
           return false;
        } else {
           OfferEntity offerEntity = offerOpt.get();

           return isAdmin(caller.get()) ||
                   offerEntity.getSeller().getUsername().equalsIgnoreCase(username);
           }
       }

    private boolean isAdmin(UserEntity user) {
        return user.getRoles().stream()
                .map(UserRoleEntity::getName)
                .anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    @Override
    public Long updateOffer(OfferServiceModel offerServiceModel) {

        OfferEntity offer = offerRepo.findById(offerServiceModel.getId()).orElseThrow();

        offer.setId(offerServiceModel.getId());
        offer.setPrice(offerServiceModel.getPrice());
        offer.setEngine(offerServiceModel.getEngine());
        offer.setTransmission(offerServiceModel.getTransmission());
        offer.setYear(offerServiceModel.getYear());
        offer.setMileage(offerServiceModel.getMileage());
        offer.setDescription(offerServiceModel.getDescription());
        offer.setImageUrl(offerServiceModel.getImageUrl());

        return offerRepo.save(offer).getId();
    }
}
