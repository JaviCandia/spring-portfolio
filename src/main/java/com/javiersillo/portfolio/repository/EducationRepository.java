package com.javiersillo.portfolio.repository;

import com.javiersillo.portfolio.model.Education;

import java.util.List;
import java.util.Optional;

public interface EducationRepository {
    Education save(Education education);

    Optional<Education> findById(Long id);

    List<Education> findAll();

    void deleteById(Long id);

    List<Education> findByPersonalInfoId(Long personalInfoId);
}