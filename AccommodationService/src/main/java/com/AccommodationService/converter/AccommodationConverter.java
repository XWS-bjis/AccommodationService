package com.AccommodationService.converter;

import com.AccommodationService.dto.AccommodationDTO;
import com.AccommodationService.model.Accommodation;
import org.springframework.stereotype.Service;

@Service
public class AccommodationConverter implements Converter<Accommodation, AccommodationDTO>{
    private final EnumConverter enumConverter;
    public AccommodationConverter(EnumConverter enumConverter){
        this.enumConverter = enumConverter;
    }
    @Override
    public AccommodationDTO entityToDto(Accommodation accommodation) {
        return AccommodationDTO.builder()
                .id(accommodation.getId())
                .hostId(accommodation.getHostId())
                .name(accommodation.getName())
                .address(accommodation.getAddress())
                .offers(accommodation.getOffers())
                .pictures(accommodation.getPictures())
                .minimalAllowedGuests(accommodation.getMinimalAllowedGuests())
                .maximalAllowedGuests(accommodation.getMaximalAllowedGuests())
                .typeOfPayment(accommodation.getTypeOfPayment().toString())
                .price(accommodation.getPrice())
                .isAutoConfirmed(accommodation.getIsAutoConfirmed())
                .build();
    }

    @Override
    public Accommodation dtoToEntity(AccommodationDTO accommodationDTO) {
        return Accommodation.builder()
                .id(accommodationDTO.getId())
                .hostId(accommodationDTO.getHostId())
                .name(accommodationDTO.getName())
                .address(accommodationDTO.getAddress())
                .offers(accommodationDTO.getOffers())
                .pictures(accommodationDTO.getPictures())
                .minimalAllowedGuests(accommodationDTO.getMinimalAllowedGuests())
                .maximalAllowedGuests(accommodationDTO.getMaximalAllowedGuests())
                .typeOfPayment(enumConverter.stringToTypeOfPayment(accommodationDTO.getTypeOfPayment()))
                .price(accommodationDTO.getPrice())
                .isAutoConfirmed(accommodationDTO.getIsAutoConfirmed())
                .build();
    }
}
