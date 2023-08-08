package com.example.mobilelele.service;

import com.example.mobilelele.model.service.OfferServiceModel;
import com.example.mobilelele.model.view.AddOfferViewModel;
import com.example.mobilelele.model.view.OfferSummaryViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {

    List<OfferSummaryViewModel> getAllOffers();

    Long saveOffer(AddOfferViewModel addOfferViewModel, String username);

    OfferServiceModel findOfferById(Long id);

    void delete(Long id);

    boolean isOwner(String username, Long id);

    Long updateOffer(OfferServiceModel offerServiceModel);
}
