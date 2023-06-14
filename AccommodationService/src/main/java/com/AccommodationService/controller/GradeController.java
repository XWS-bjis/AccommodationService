package com.AccommodationService.controller;

import com.AccommodationService.model.Accommodation;
import com.AccommodationService.model.Grade;
import com.AccommodationService.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grade")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("/accommodation/{accommodationId}")
    public ResponseEntity create(@RequestBody Grade grade, @PathVariable("accommodationId") String accommodationId) {
        gradeService.create(grade, accommodationId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/accommodation/{accommodationId}/{userId}")
    public ResponseEntity delete(@PathVariable("accommodationId") String accommodationId, @PathVariable("userId") String userId) {
        gradeService.delete(accommodationId, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/accommodation/{accommodationId}")
    public ResponseEntity edit(@RequestBody Grade grade, @PathVariable("accommodationId") String accommodationId) {
        gradeService.edit(grade, accommodationId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
