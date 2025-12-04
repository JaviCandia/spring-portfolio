package com.javiersillo.portfolio.service;

import com.javiersillo.portfolio.model.PersonalInfo;
import com.javiersillo.portfolio.repository.PersonalInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInfoService {

    private final PersonalInfoRepository personalInfoRepository;

    // Se podría ocupar @RequiredArgsConstructor en vez de esta inyección si lo prefieres
    public PersonalInfoService(PersonalInfoRepository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }

    public PersonalInfo save(PersonalInfo personalInfo) {
        return personalInfoRepository.save(personalInfo);
    }

    public Optional<PersonalInfo> findById(Long id){
        return personalInfoRepository.findById(id);
    }

    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
