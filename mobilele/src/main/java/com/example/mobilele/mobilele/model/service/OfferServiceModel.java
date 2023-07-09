package com.example.mobilele.mobilele.model.service;

import com.example.mobilele.mobilele.model.entities.enums.EngineEnum;
import com.example.mobilele.mobilele.model.entities.enums.TransmissionEnum;
import com.example.mobilele.mobilele.model.validation.YearInPastOrPresent;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferServiceModel {

    @NotNull
    private EngineEnum engine;
    @NotEmpty
    private String imageUrl;
    @NotNull
    @PositiveOrZero
    private int mileage;
    @NotNull
    @DecimalMin("100")
    private BigDecimal price;
    @YearInPastOrPresent(minYear = 1930)
    private int year;
    @NotEmpty
    @Size(min = 10, max = 512)
    private String description;
    @NotNull
    private TransmissionEnum transmission;
    @NotNull
    private Long modelId;

    public OfferServiceModel() {
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferServiceModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferServiceModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferServiceModel setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferServiceModel setYear(int year) {
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

    public Long getModelId() {
        return modelId;
    }

    public OfferServiceModel setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    @Override
    public String toString() {
        return "OfferServiceModel{" +
                "engine=" + engine +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", transmission=" + transmission +
                ", modelId=" + modelId +
                '}';
    }
}
