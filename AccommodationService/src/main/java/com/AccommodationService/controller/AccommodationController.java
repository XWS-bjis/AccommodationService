package com.AccommodationService.controller;

import com.AccommodationService.converter.AccommodationConverter;
import com.AccommodationService.converter.EnumConverter;
import com.AccommodationService.dto.AccommodationDTO;
import com.AccommodationService.dto.AccommodationFilterDTO;
import com.AccommodationService.model.Accommodation;
import com.AccommodationService.model.Address;
import com.AccommodationService.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accommodation")
public class AccommodationController {

    private AccommodationService accommodationService;
    private final AccommodationConverter accommodationConverter;
    private final EnumConverter enumConverter;
    @Autowired
    public AccommodationController(AccommodationService accommodationService, EnumConverter enumConverter, AccommodationConverter accommodationConverter){
        this.enumConverter = enumConverter;
        this.accommodationConverter = accommodationConverter;
        this.accommodationService = accommodationService;
    }

    @PostMapping
    public ResponseEntity<Accommodation> create(@RequestBody Accommodation accommodation){
        Accommodation newFlight = accommodationService.create(accommodation);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Accommodation>> getAllAccommodations(){
            List<Accommodation> accommodations = accommodationService.getAllAccommodations();
            return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<AccommodationDTO>> search(@RequestBody AccommodationFilterDTO request) throws ParseException {
        List<Accommodation> searchedData = accommodationService.filter(request.getLocation(), request.getGuests());
        if(searchedData != null) {
            List<AccommodationDTO> dtos = searchedData.stream().map(accommodationConverter::entityToDto).toList();
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.ok(new ArrayList<AccommodationDTO>());
        }
    }
}
