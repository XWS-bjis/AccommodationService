package com.AccommodationService.repository;

import com.AccommodationService.model.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface AccommodationRepository extends MongoRepository<Accommodation, String>, QuerydslPredicateExecutor<Accommodation> {

    List<Accommodation> findAllByAddressTownAndMaximalAllowedGuestsGreaterThanEqualAndMinimalAllowedGuestsLessThanEqual(String location, int guests, int guests1);

    List<Accommodation> findAllByOffersWifiAndOffersParkingAndOffersKitchenAndOffersAirConditionerAndOffersPetsAllowedAndPriceRegularPriceBetween(boolean wifi, boolean parking, boolean kitchen, boolean airConditioner, boolean pets, int maxPrice, int minPrice);

    List<Accommodation> findAllByOffersWifiAndOffersParkingAndOffersKitchenAndOffersAirConditionerAndOffersPetsAllowed(boolean wifi, boolean parking, boolean kitchen, boolean airConditioner, boolean pets);

    List<Accommodation> findAllByOffersParkingAndOffersKitchenAndOffersAirConditionerAndOffersPetsAllowed(boolean parking, boolean kitchen, boolean airConditioner, boolean pets);
    List<Accommodation> findAllByOffersWifiAndOffersKitchenAndOffersAirConditionerAndOffersPetsAllowed(boolean wifi, boolean kitchen, boolean airConditioner, boolean pets);
//    List<Accommodation> findAllByOffersWifiAndOffersParkingAndOffersKitchenAndOffersAirConditionerAndOffersPetsAllowed(boolean wifi, boolean parking, boolean kitchen, boolean airConditioner, boolean pets);
//    List<Accommodation> findAllByOffersWifiAndOffersParkingAndOffersKitchenAndOffersAirConditionerAndOffersPetsAllowed(boolean wifi, boolean parking, boolean kitchen, boolean airConditioner, boolean pets);
//    List<Accommodation> findAllByOffersWifiAndOffersParkingAndOffersKitchenAndOffersAirConditionerAndOffersPetsAllowed(boolean wifi, boolean parking, boolean kitchen, boolean airConditioner, boolean pets);

}
