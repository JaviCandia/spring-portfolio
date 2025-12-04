package com.javiersillo.portfolio.controller;

import com.javiersillo.portfolio.model.Skill;
import com.javiersillo.portfolio.service.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillsController {
    private final SkillsService skillsService;

    @GetMapping
    public List<Skill> getAll() {
        return skillsService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Skill> getById(@PathVariable Long id) {
        return skillsService.findById(id);
    }

    // @ResponseStatus(HttpStatus.CREATED) esto evitaría tener que usear ResponseEntity<Skill>
    @PostMapping
    public ResponseEntity<Skill> create(@RequestBody Skill skill) {
        Skill newSkill = skillsService.save(skill);
        return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill skill) {
        skill.setId(id);
        return skillsService.save(skill);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        skillsService.deleteById(id);
    }

    @GetMapping("/personal-info/{id}")
    public List<Skill> getByPersonalInfoId(@PathVariable Long id) {
        return skillsService.findByPersonalInfoId(id);
    }
}
