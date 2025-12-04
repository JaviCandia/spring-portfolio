package com.javiersillo.portfolio.service;

import com.javiersillo.portfolio.model.Skill;
import com.javiersillo.portfolio.repository.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillsService {
    private final SkillsRepository skillsRepository;

    public Skill save(Skill skill) {

        // Al ser service aquí metemos la lógica de neogcio.
        // Esto solo es para practicar porque meteremos excepciones
        if(skill.getLevelPercentage() < 0 || skill.getLevelPercentage() > 100 ) {
            throw new IllegalArgumentException("El porcentaje debe ser entre 0 y 100");
        }
        return skillsRepository.save(skill);
    }

    public Optional<Skill> findById(Long id) {
        return skillsRepository.findById(id);
    }

    public List<Skill> findAll() {
        return skillsRepository.findAll();
    }

    public void deleteById(Long id) {
        skillsRepository.deleteById(id);
    }

    public List<Skill> findByPersonalInfoId(Long skillId) {
        return skillsRepository.findByPersonalInfoId(skillId);
    }
}
