package com.example.mobilelele.model.service;

import com.example.mobilelele.model.entity.ModelEntity;
import com.example.mobilelele.model.entity.UserEntity;
import com.example.mobilelele.model.entity.enums.EngineEnum;
import com.example.mobilelele.model.entity.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferServiceModel {
    private Long id;
    private ModelEntity model;
    private BigDecimal price;
    private EngineEnum engine;
    private Integer mileage;
    private TransmissionEnum transmission;
    private Integer year;
    private String description;
    private String imageUrl;
    private UserEntity seller;
    protected Instant created;
    protected Instant modified;

    public Long getId() {
        return id;
    }

    public OfferServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public ModelEntity getModel() {
        return model;
    }

    public OfferServiceModel setModel(ModelEntity model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferServiceModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferServiceModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferServiceModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferServiceModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferServiceModel setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OfferServiceModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OfferServiceModel setModified(Instant modified) {
        this.modified = modified;
        return this;
    }
}
