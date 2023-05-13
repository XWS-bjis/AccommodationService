package com.AccommodationService.controller;

import com.AccommodationService.model.Accommodation;
import com.AccommodationService.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @PostMapping
    public ResponseEntity<Accommodation> create(@RequestBody Accommodation accommodation){
        Accommodation newFlight = accommodationService.create(accommodation);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }
}
