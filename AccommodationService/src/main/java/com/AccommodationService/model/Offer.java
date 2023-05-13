package com.AccommodationService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    private boolean wifi;
    private boolean parking;
    private boolean kitchen;
    private boolean airConditioner;
    private boolean petsAllowed;
}
