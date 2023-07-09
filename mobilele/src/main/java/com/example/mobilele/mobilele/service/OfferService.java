package com.example.mobilele.mobilele.service;

import com.example.mobilele.mobilele.model.service.OfferServiceModel;
import com.example.mobilele.mobilele.model.view.OfferSummaryViewModel;

import java.util.List;

public interface OfferService {

    List<OfferSummaryViewModel> getAllOffers();

    long save(OfferServiceModel model);

    void delete(long id);
}
