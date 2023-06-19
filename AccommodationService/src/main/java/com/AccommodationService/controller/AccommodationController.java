package com.AccommodationService.controller;

import com.AccommodationService.converter.AccommodationConverter;
import com.AccommodationService.converter.EnumConverter;
import com.AccommodationService.dto.AccommodationDTO;
import com.AccommodationService.dto.AccommodationFilterDTO;
import com.AccommodationService.dto.AccommodationSideFilterDTO;
import com.AccommodationService.model.Accommodation;
import com.AccommodationService.model.Address;
import com.AccommodationService.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accommodation")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/filter")
    public ResponseEntity<List<AccommodationDTO>> search(@RequestBody AccommodationFilterDTO request) throws ParseException {
        List<Accommodation> searchedData = accommodationService.filter(request.getLocation(), request.getGuests());
        if (searchedData != null) {
            List<AccommodationDTO> dtos = searchedData.stream().map(accommodationConverter::entityToDto).toList();
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.ok(new ArrayList<AccommodationDTO>());
        }
    }

    @PostMapping("/sidefilter")
    public ResponseEntity<List<AccommodationDTO>> sideFilter(@RequestBody AccommodationSideFilterDTO request) throws ParseException {
        System.out.println("Isidora");
        System.out.println(request.getOffer());
        System.out.println("KOntroler: "+request.isFeaturedHost());
        List<Accommodation> searchedData = accommodationService.sideFilter(request.getOffer(), request.getMinPrice(), request.getMaxPrice(), request.isFeaturedHost());
        if (searchedData != null) {
            List<AccommodationDTO> dtos = searchedData.stream().map(accommodationConverter::entityToDto).toList();
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.ok(new ArrayList<AccommodationDTO>());
        }
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> getAll() {
        return new ResponseEntity<>(accommodationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/visited")
    public ResponseEntity<List<Accommodation>> getAllVisitedByUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(accommodationService.getAllVisitedByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(accommodationService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/host-identifiers/guest/{id}")
    public ResponseEntity<List<String>> getAllIds(@PathVariable("id") String id) {
        return new ResponseEntity<>(accommodationService.getAllIds(id), HttpStatus.OK);
    }

}
