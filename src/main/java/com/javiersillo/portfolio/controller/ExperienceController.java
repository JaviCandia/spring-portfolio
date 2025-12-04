package com.javiersillo.portfolio.controller;

import com.javiersillo.portfolio.model.Experience;
import com.javiersillo.portfolio.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/experience")
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping
    public List<Experience> getAll() {
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public Experience getById(@PathVariable Long id) {
        return experienceService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // <--- Perfecto, mantén esto
    public Experience create(@RequestBody Experience education) {
        return experienceService.save(education);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Experience update(@PathVariable Long id, @RequestBody Experience education) {
        education.setId(id);
        return experienceService.save(education);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        experienceService.deleteById(id);
    }

    @GetMapping("/personal-info/{id}")
    public List<Experience> getByPersonalInfoId(@PathVariable Long id) {
        return experienceService.findByPersonalInfoId(id);
    }
}
