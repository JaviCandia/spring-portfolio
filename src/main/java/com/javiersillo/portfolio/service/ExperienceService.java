package com.javiersillo.portfolio.service;

import com.javiersillo.portfolio.model.Education;
import com.javiersillo.portfolio.model.Experience;
import com.javiersillo.portfolio.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    public Experience save(Experience experience) {
        // Validación 1: Asegurar que la fecha de inicio no sea nula
        if (experience.getStartDate() == null) {
            throw new IllegalArgumentException("La fecha de inicio de la experiencia no puede estar vacía.");
        }

        // Validación 2: La fecha de inicio no puede ser posterior a la de fin (solo si end_date no es nula)
        if (experience.getEndDate() != null
                && experience.getStartDate().isAfter(experience.getEndDate())
        ) {
            throw new IllegalArgumentException("La fecha de inicio de la experiencia no puede ser posterior a la fecha de fin.");
        }

        // Validaciones 3 y 4 (ya estaban bien):
        if (experience.getJobTitle() == null || experience.getJobTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("El título del trabajo no puede estar vacío.");
        }
        if (experience.getCompanyName() == null || experience.getCompanyName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la compañía no puede estar vacío.");
        }
        return experienceRepository.save(experience);
    }

    public void deleteById(Long id) {
        System.out.println("Eliminando experiencia por ID: " + id + " en el servicio...");
        experienceRepository.deleteById(id);
    }

    public List<Experience> findByPersonalInfoId(Long personalInfoId) {
        return experienceRepository.findByPersonalInfoId(personalInfoId);
    }
}
