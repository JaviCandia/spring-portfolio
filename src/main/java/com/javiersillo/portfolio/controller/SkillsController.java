package com.javiersillo.portfolio.controller;

import com.javiersillo.portfolio.model.Skill;
import com.javiersillo.portfolio.service.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillsController {
    private final SkillsService skillsService;

    @GetMapping("all")
    public List<Skill> getAll() {
        return skillsService.findAll();
    }

    @GetMapping("/{id}")
    public Skill getById(@PathVariable Long id) {
        Optional<Skill> skill = skillsService.findById(id);
        if (skill.isPresent()) {
            return skill.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personal info not found in id: " + id);
        }
    }


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
