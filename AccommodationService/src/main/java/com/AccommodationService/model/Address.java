package com.AccommodationService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String id;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String town;
    private String country;
}
