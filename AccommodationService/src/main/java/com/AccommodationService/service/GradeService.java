package com.AccommodationService.service;

import com.AccommodationService.model.Grade;
import com.AccommodationService.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final AccommodationRepository accommodationRepository;

    public void create(Grade grade, String accommodationId) {
        var optionalAccommodation = accommodationRepository.findById(accommodationId);
        if (optionalAccommodation.isEmpty()) return;
        var accommodation = optionalAccommodation.get();
        var grades = accommodation.getGrades();
        if (grades == null) grades = new ArrayList<Grade>();
        if(userGradeAlreadyExists(grades, grade.getReviewerId())) return;
        grade.setCreatedAt(LocalDateTime.now());
        grades.add(grade);
        accommodation.setGrades(grades);
        accommodation.setAvgGrade(calculateAvgGrade(grades));
        accommodationRepository.save(accommodation);
    }

    public Double calculateAvgGrade(List<Grade> grades) {
        double total = 0;
        double count = 0;
        for (var grade: grades) {
            total += grade.getValue();
            count++;
        }
        return total/count;
    }

    public void delete(String accommodationId, String userId) {
        var optionalAccommodation = accommodationRepository.findById(accommodationId);
        if (optionalAccommodation.isEmpty()) return;
        var accommodation = optionalAccommodation.get();
        var grades = accommodation.getGrades();
        if (grades == null) grades = new ArrayList<Grade>();
        grades = getGradeListWithoutUserGrade(grades, userId);
        accommodation.setGrades(grades);
        accommodation.setAvgGrade(calculateAvgGrade(grades));
        accommodationRepository.save(accommodation);
    }

    private List<Grade> getGradeListWithoutUserGrade(List<Grade> grades, String userId) {
        int indexForDeletion = 0;
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).getReviewerId().equals(userId)) indexForDeletion = i;
        }
        grades.remove(indexForDeletion);
        return grades;
    }

    private boolean userGradeAlreadyExists(List<Grade> grades, String userId) {
        var exists = false;
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).getReviewerId().equals(userId)) exists = true;
        }
        return exists;
    }

    public void edit(Grade grade, String accommodationId) {
        delete(accommodationId, grade.getReviewerId());
        create(grade, accommodationId);
    }
}
