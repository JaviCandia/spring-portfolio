package com.javiersillo.portfolio.repository;

import com.javiersillo.portfolio.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillsRepository {
    Skill save(Skill skill);
    Optional<Skill> findById(Long id);
    List<Skill> findAll();
    void deleteById(Long id);
    List<Skill> findByPersonalInfoId(Long personalInfoId);
}
