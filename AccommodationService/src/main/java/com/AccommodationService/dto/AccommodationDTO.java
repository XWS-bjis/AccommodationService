package com.AccommodationService.dto;

import com.AccommodationService.model.Address;
import com.AccommodationService.model.Offer;
import com.AccommodationService.model.Price;
import com.AccommodationService.model.enums.TypeOfPayment;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccommodationDTO {
    String id;
    Long hostId;
    String name;
    Address address;
    Offer offers;
    String pictures;
    int minimalAllowedGuests;
    int maximalAllowedGuests;
    String typeOfPayment;
    Price price;
    Boolean isAutoConfirmed;
}
