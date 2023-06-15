package com.AccommodationService.service;

import com.AccommodationService.model.Accommodation;
import com.AccommodationService.model.Offer;
import com.AccommodationService.model.QAccommodation;
import com.AccommodationService.repository.AccommodationRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;
    public Accommodation create(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    public List<Accommodation> filter(String location, int guests) {
        return accommodationRepository.findAllByAddressTownAndMaximalAllowedGuestsGreaterThanEqualAndMinimalAllowedGuestsLessThanEqual(location, guests, guests);
    }

    public List<Accommodation> sideFilter(Offer offer, int minPrice, int maxPrice, boolean featuredHost) {
        List<Accommodation> accommodations;
        QAccommodation qAccommodation = QAccommodation.accommodation;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAccommodation.price.regularPrice.between(minPrice, maxPrice));
        if (offer.isWifi()){
            builder.and(qAccommodation.offers.wifi.isTrue());
        }
        if (offer.isKitchen()){
            builder.and(qAccommodation.offers.kitchen.isTrue());
        }
        if (offer.isPetsAllowed()){
            builder.and(qAccommodation.offers.petsAllowed.isTrue());
        }
        if (offer.isParking()){
            builder.and(qAccommodation.offers.parking.isTrue());
        }
        if (offer.isAirConditioner()){
            builder.and(qAccommodation.offers.airConditioner.isTrue());
        }
        accommodations =  Streamable.of(accommodationRepository.findAll(builder.getValue())).toList();

        if (featuredHost) {
//            Filter out accommodations without featured host
        }
        return accommodations;
    }

    public List<Accommodation> getAll() {
        return accommodationRepository.findAll();
    }
}
