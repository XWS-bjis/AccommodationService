package com.AccommodationService.service;

import com.AccommodationService.model.Accommodation;
import com.AccommodationService.model.Offer;
import com.AccommodationService.model.QAccommodation;
import com.AccommodationService.repository.AccommodationRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccommodationService {

    @Autowired
    private RestTemplate restTemplate;
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
            String url = "http://user:8084/api/v1/user/featured-hosts";
            ResponseEntity<List<String>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<String>>() {}
            );

            List<Accommodation> filterResult = new ArrayList<>();
            if(response.getBody().size() > 0){
                for (String hostId: response.getBody()){
                    for(Accommodation accommodation: accommodations){
                        if(accommodation.getHostId().equals(hostId)){
                            filterResult.add(accommodation);
                        }
                    }
                }
            }
            return filterResult;
        }
        return accommodations;
    }

    public List<Accommodation> getAll() {
        return accommodationRepository.findAll();
    }

    public List<Accommodation> getAllVisitedByUser(String userId) {
        var accommodationIdsList = getAllVisitedIdsByUser(userId);
        return accommodationRepository.findAllById(accommodationIdsList);
    }

    private List<String> getAllVisitedIdsByUser(String userId) {
        String url = "http://reservation-service:8083/api/reservation/user/" + userId + "/visited";
        ResponseEntity<List<String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // Handle error case
            throw new RuntimeException("Request failed with status code: " + response.getStatusCode());
        }
    }

    public Accommodation getById(String id) {
        var optionalAccommodation = accommodationRepository.findById(id);
        if (optionalAccommodation.isEmpty()) return null;
        return optionalAccommodation.get();
    }

    public List<String> getAllIds(String id){
        List<Accommodation> accommodations = accommodationRepository.findAll();
        String reservationControllerURL = "http://reservation-service:8083/api/reservation/history/user/"+id;
        ResponseEntity<List<String>> response = restTemplate.exchange(
                reservationControllerURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );
        List<String> hostIds = new ArrayList<>();

        if(response.getBody().size() > 0){
            for(String accommodationId: response.getBody()){
                for(Accommodation a: accommodations){
                    if(a.getId().equals(accommodationId)){
                        hostIds.add(a.getHostId());
                    }
                }
            }
        }
        return hostIds;
    }
}
