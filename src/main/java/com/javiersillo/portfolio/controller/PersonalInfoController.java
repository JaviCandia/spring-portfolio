package com.javiersillo.portfolio.controller;

import com.javiersillo.portfolio.model.PersonalInfo;
import com.javiersillo.portfolio.service.PersonalInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {

    private final PersonalInfoService personalInfoService;

    public PersonalInfoController(PersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping
    public List<PersonalInfo> getAll() {
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public PersonalInfo getById(@PathVariable Long id) {
        Optional<PersonalInfo> info = personalInfoService.findById(id);
        if (info.isPresent()) {
            return info.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personal info not found in id: " + id);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonalInfo create(@RequestBody PersonalInfo personalInfo) {
        return personalInfoService.save(personalInfo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonalInfo update(@PathVariable Long id, @RequestBody PersonalInfo personalInfo) {
        personalInfo.setId(id);
        return personalInfoService.save(personalInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personalInfoService.deleteById(id);
    }
}
