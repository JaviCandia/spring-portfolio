package com.javiersillo.portfolio.service;

import com.javiersillo.portfolio.exception.ValidationException;
import com.javiersillo.portfolio.model.PersonalInfo;
import com.javiersillo.portfolio.repository.PersonalInfoRepository;
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
public class PersonalInfoService {

    private final PersonalInfoRepository personalInfoRepository;
    private final Validator validator;

    @Transactional
    public PersonalInfo save(PersonalInfo personalInfo) {

        BindingResult bindingResult = new BeanPropertyBindingResult(personalInfo, "personalInfo");
        validator.validate(personalInfo, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return personalInfoRepository.save(personalInfo);
    }

    @Transactional(readOnly = true)
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
