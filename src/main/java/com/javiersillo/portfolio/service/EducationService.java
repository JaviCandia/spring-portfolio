package com.javiersillo.portfolio.service;

import com.javiersillo.portfolio.model.Education;
import com.javiersillo.portfolio.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;

    public Education save(Education education) {
        // Validación 1: Asegurar que la fecha de inicio no sea nula, como exige la DB
        if (education.getStartDate() == null) {
            throw new IllegalArgumentException("La fecha de inicio de la educación no puede estar vacía.");
        }

        // Validación 2: La fecha de inicio no puede ser posterior a la de fin
        if (education.getEndDate() != null && education.getStartDate().isAfter(education.getEndDate())) {
            throw new IllegalArgumentException("La fecha de inicio de la educación no puede ser posterior a la fecha de fin.");
        }

        return educationRepository.save(education);
    }

    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    public void deleteById(Long id) {
        System.out.println("Eliminando educación por ID: " + id + " en el servicio...");
        educationRepository.deleteById(id);
    }

    public List<Education> findByPersonalInfoId(Long personalInfoId) {
        return educationRepository.findByPersonalInfoId(personalInfoId);
    }
}
