package com.AccommodationService.model;

import com.AccommodationService.model.enums.TypeOfPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accommodation {
    private String id;
    private Long hostId;
    private String name;
    private Address address;
    private Offer offers;
    private List<String> pictures;
    private Integer minimalAllowedGuests;
    private Integer maximalAllowedGuests;
    private TypeOfPayment typeOfPayment;
    private Price price;
    private Boolean isAutoConfirmed;
}
