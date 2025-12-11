package com.javiersillo.portfolio.service;

import com.javiersillo.portfolio.exception.ValidationException;
import com.javiersillo.portfolio.model.Skill;
import com.javiersillo.portfolio.repository.SkillsRepository;
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
public class SkillsService {
    private final SkillsRepository skillsRepository;
    private final Validator validator;

    @Transactional
    public Skill save(Skill skill) {
        BindingResult bindingResult = new BeanPropertyBindingResult(skill, "skill");
        validator.validate(skill, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        return skillsRepository.save(skill);
    }

    @Transactional(readOnly = true)
    public Optional<Skill> findById(Long id) {
        return skillsRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Skill> findAll() {
        return skillsRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        skillsRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Skill> findByPersonalInfoId(Long skillId) {
        return skillsRepository.findByPersonalInfoId(skillId);
    }
}
