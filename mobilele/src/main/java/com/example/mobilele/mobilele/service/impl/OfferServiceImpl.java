package com.example.mobilele.mobilele.service.impl;

import com.example.mobilele.mobilele.model.entities.OfferEntity;
import com.example.mobilele.mobilele.model.service.OfferServiceModel;
import com.example.mobilele.mobilele.model.view.OfferSummaryViewModel;
import com.example.mobilele.mobilele.repository.ModelRepository;
import com.example.mobilele.mobilele.repository.OfferRepository;
import com.example.mobilele.mobilele.repository.UserRepository;
import com.example.mobilele.mobilele.security.CurrentUser;
import com.example.mobilele.mobilele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, CurrentUser currentUser, UserRepository userRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<OfferSummaryViewModel> getAllOffers() {

        List<OfferEntity> allOffers = offerRepository.findAll();
        List<OfferSummaryViewModel> result = new ArrayList<>();

        allOffers.forEach(offer -> {

        });
        return null;
    }

    @Override
    public long save(OfferServiceModel model) {
        OfferEntity offerEntity = asNewEntity(model);
        OfferEntity newEntity = offerRepository.save(offerEntity);
        return newEntity.getId();
    }

    private OfferEntity asNewEntity(OfferServiceModel model) {
        OfferEntity offerEntity = new OfferEntity();
        offerEntity = modelMapper.map(model, OfferEntity.class);
        offerEntity.setId(null);
        offerEntity.setModel(modelRepository.findById(model.getModelId()).orElseThrow());
        offerEntity.setUser(userRepository.findByUsername(currentUser.getName()).orElseThrow());
        return offerEntity;
    }
}
