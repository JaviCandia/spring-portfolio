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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Skill create(@RequestBody Skill education) {
        return skillsService.save(education);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Skill update(@PathVariable Long id, @RequestBody Skill skill) {
        skill.setId(id);
        return skillsService.save(skill);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        skillsService.deleteById(id);
    }

    @GetMapping("/personal-info/{id}")
    public List<Skill> getByPersonalInfoId(@PathVariable Long id) {
        return skillsService.findByPersonalInfoId(id);
    }
}
