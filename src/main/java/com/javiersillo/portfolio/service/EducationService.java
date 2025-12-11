package com.javiersillo.portfolio.service;

import com.javiersillo.portfolio.exception.ValidationException;
import com.javiersillo.portfolio.model.Education;
import com.javiersillo.portfolio.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;
    private final Validator validator;

    @Transactional
    public Education save(Education education) {
        BindingResult bindingResult = new BeanPropertyBindingResult(education, "education");
        validator.validate(education, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        return educationRepository.save(education);
    }

    @Transactional(readOnly = true)
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        System.out.println("Eliminando educación por ID: " + id + " en el servicio...");
        educationRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Education> findByPersonalInfoId(Long personalInfoId) {
        return educationRepository.findByPersonalInfoId(personalInfoId);
    }
}
