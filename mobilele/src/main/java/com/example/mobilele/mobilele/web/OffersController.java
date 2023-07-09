package com.example.mobilele.mobilele.web;

import com.example.mobilele.mobilele.model.entities.enums.EngineEnum;
import com.example.mobilele.mobilele.model.entities.enums.TransmissionEnum;
import com.example.mobilele.mobilele.model.service.OfferServiceModel;
import com.example.mobilele.mobilele.service.BrandService;
import com.example.mobilele.mobilele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OfferService offerService;
    private final BrandService brandService;

    @Autowired
    public OffersController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @ModelAttribute("offerModel")
    public OfferServiceModel offerModel() {
        return new OfferServiceModel();
    }

    @GetMapping("/add")
    public String newOffer(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@ModelAttribute OfferServiceModel offerModel) {

        //todo: validation

        offerService.save(offerModel);

        return "redirect:/offers/all";
    }

    @GetMapping("all")
    public String getAllOffers(Model model) {
//        model.addAttribute("models", offerService.getAllOffers());
        return "offers";
    }
}
