package com.AccommodationService.repository;

import com.AccommodationService.model.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends MongoRepository<Accommodation, String> {

}
