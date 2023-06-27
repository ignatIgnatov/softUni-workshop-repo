package com.example.mobilele.mobilele.service.impl;

import com.example.mobilele.mobilele.model.entities.OfferEntity;
import com.example.mobilele.mobilele.model.view.OfferSummaryViewModel;
import com.example.mobilele.mobilele.repository.OfferRepository;
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

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OfferSummaryViewModel> getAllOffers() {

        List<OfferEntity> allOffers = offerRepository.findAll();
        List<OfferSummaryViewModel> result = new ArrayList<>();

        allOffers.forEach(offer -> {
            
        });
        return null;
    }
}
