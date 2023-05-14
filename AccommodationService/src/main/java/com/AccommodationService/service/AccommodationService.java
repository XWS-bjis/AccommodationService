package com.AccommodationService.service;

import com.AccommodationService.model.Accommodation;
import com.AccommodationService.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;
    public Accommodation create(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    public List<Accommodation> getAll() {
        return accommodationRepository.findAll();
    }
}
