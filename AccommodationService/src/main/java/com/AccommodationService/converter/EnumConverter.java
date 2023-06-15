package com.AccommodationService.converter;

import com.AccommodationService.model.enums.TypeOfPayment;
import org.springframework.stereotype.Service;

@Service
public class EnumConverter {
    public TypeOfPayment stringToTypeOfPayment(String input){
        switch (input.trim().toUpperCase()){
            case "PER_PERSON":
                return TypeOfPayment.PER_PERSON;
            case "PER_NIGHT":
                return TypeOfPayment.PER_NIGHT;
            default:
                return TypeOfPayment.NONE;
        }
    }
}
