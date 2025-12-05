package com.javiersillo.portfolio.controller;

import com.javiersillo.portfolio.model.Education;
import com.javiersillo.portfolio.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    @GetMapping
    public List<Education> getAll() {
        return educationService.findAll();
    }

    @GetMapping("/{id}")
    public Education getById(@PathVariable Long id) {
        return educationService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Education create(@RequestBody Education education) {
        return educationService.save(education);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Education update(@PathVariable Long id, @RequestBody Education education) {
        education.setId(id);
        return educationService.save(education);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        educationService.deleteById(id);
    }

    @GetMapping("/personal-info/{id}")
    public List<Education> getByPersonalInfoId(@PathVariable Long id) {
        return educationService.findByPersonalInfoId(id);
    }
}