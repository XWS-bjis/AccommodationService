package com.AccommodationService.repository;

import com.AccommodationService.model.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccommodationRepository extends MongoRepository<Accommodation, String> {

}
