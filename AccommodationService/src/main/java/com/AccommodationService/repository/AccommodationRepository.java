package com.AccommodationService.repository;

import com.AccommodationService.model.Accommodation;
import com.AccommodationService.model.Address;
import com.AccommodationService.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccommodationRepository extends MongoRepository<Accommodation, String> {

    List<Accommodation> findAllByAddressTownAndMaximalAllowedGuestsGreaterThanEqualAndMinimalAllowedGuestsLessThanEqual(String location, int guests, int guests1);

    List<Accommodation> findAllByOffersWifiAndOffersParkingAndOffersKitchenAndOffersAirConditionerAndOffersPetsAllowedAndPriceRegularPriceBetween(boolean wifi, boolean parking, boolean kitchen, boolean airConditioner, boolean pets, int maxPrice, int minPrice);
}
