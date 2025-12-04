package com.javiersillo.portfolio.repository;

import com.javiersillo.portfolio.model.PersonalInfo;

import java.util.List;
import java.util.Optional;

public interface PersonalInfoRepository {
    PersonalInfo save(PersonalInfo personalInfo);

    Optional<PersonalInfo> findById(Long id);

    List<PersonalInfo> findAll();

    void deleteById(Long id);
}
