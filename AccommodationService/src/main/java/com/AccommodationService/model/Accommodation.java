package com.AccommodationService.model;

import com.AccommodationService.model.enums.TypeOfPayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accommodation {
    private String id;
    private Long hostId;
    private String name;
    private Address address;
    private Offer offers;
    private List<String> pictures;
    private int minimalAllowedGuests;
    private int maximalAllowedGuests;
    private TypeOfPayment typeOfPayment;
    private Boolean isAutoConfirmed;
}
