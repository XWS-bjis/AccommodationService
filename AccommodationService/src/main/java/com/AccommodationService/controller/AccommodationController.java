package com.AccommodationService.controller;

import com.AccommodationService.model.Accommodation;
import com.AccommodationService.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accommodation")
@CrossOrigin(origins = "http://localhost:4200")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @PostMapping
    public ResponseEntity<Accommodation> create(@RequestBody Accommodation accommodation){
        Accommodation newFlight = accommodationService.create(accommodation);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> getAll() {
        return new ResponseEntity<>(accommodationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/visited")
    public ResponseEntity<List<Accommodation>> getAllVisitedByUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(accommodationService.getAllVisitedByUser(userId), HttpStatus.OK);
    }
}
