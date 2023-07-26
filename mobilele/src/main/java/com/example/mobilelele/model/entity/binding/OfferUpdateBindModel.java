package com.example.mobilelele.model.entity.binding;

import com.example.mobilelele.model.entity.enums.EngineEnum;
import com.example.mobilelele.model.entity.enums.TransmissionEnum;
import com.example.mobilelele.model.validation.YearInPastOrPresent;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferUpdateBindModel {
    private Long id;
    private String model;
    @NotNull(message = "Price must be greater than 100, required.")
    @DecimalMin("100")
    private BigDecimal price;
    @NotNull
    private EngineEnum engine;
    @NotNull
    private TransmissionEnum transmission;
    @YearInPastOrPresent(minYear = 1930, message = "Year must be from 1930 to 2100, required.")
    private Integer year;
    @NotNull (message = "Mileage must be Positive number ot Zero, required.")
    @PositiveOrZero
    private Integer mileage;
    @NotEmpty (message = "Description must be from 10 to 3000 chars long, required.")
    @Size(min = 10, max = 3000)
    private String description;
    @NotEmpty
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public OfferUpdateBindModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferUpdateBindModel setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferUpdateBindModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferUpdateBindModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferUpdateBindModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferUpdateBindModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferUpdateBindModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferUpdateBindModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateBindModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
