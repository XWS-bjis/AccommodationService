package com.AccommodationService.dto;

import com.AccommodationService.model.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationSideFilterDTO {
    int minPrice;
    int maxPrice;
    Offer offer;
    boolean featuredHost;
}
