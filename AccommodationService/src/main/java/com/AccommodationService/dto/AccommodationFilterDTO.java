package com.AccommodationService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationFilterDTO {
    String location;
    int guests;
    String startDate;
    String endDate;
}
