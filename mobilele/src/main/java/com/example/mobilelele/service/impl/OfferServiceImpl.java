package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.OfferEntity;
import com.example.mobilelele.model.entity.enums.EngineEnum;
import com.example.mobilelele.model.entity.enums.TransmissionEnum;
import com.example.mobilelele.model.service.OfferServiceModel;
import com.example.mobilelele.model.view.AddOfferViewModel;
import com.example.mobilelele.model.view.OfferSummaryViewModel;
import com.example.mobilelele.repository.OfferRepo;
import com.example.mobilelele.service.BrandService;
import com.example.mobilelele.service.OfferService;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final UserService userService;
    private final OfferRepo offerRepo;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    public OfferServiceImpl(UserService userService, OfferRepo offerRepo, ModelMapper modelMapper, BrandService brandService) {
        this.userService = userService;
        this.offerRepo = offerRepo;
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
