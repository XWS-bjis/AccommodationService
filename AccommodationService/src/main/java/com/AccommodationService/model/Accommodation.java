package com.AccommodationService.model;

import com.AccommodationService.model.enums.TypeOfPayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Accommodation {
    private String id;
    private String hostId;
    private String name;
    private Address address;
    private Offer offers;
    private String pictures;
    private int minimalAllowedGuests;
    private int maximalAllowedGuests;
    private Price price;
    private TypeOfPayment typeOfPayment;
    private Boolean isAutoConfirmed;
    private List<Grade> grades;
    private Double avgGrade;
}
