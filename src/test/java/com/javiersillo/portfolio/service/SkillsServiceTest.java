package com.javiersillo.portfolio.service;

import com.javiersillo.portfolio.model.Skill;
import com.javiersillo.portfolio.repository.SkillsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkillsServiceTest {

    @Mock
    private SkillsRepository skillsRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private SkillsService skillsService;

    @Test
    void shouldReturnListOfSkills_WhenDataExists() {
        // Arrange
        List<Skill> mockSkills = List.of(new Skill(), new Skill());
        when(skillsRepository.findAll()).thenReturn(mockSkills);

        // Act
        List<Skill> skills = skillsService.findAll();

        // Assert
        assertNotNull(skills);
        assertEquals(2, skills.size());
        verify(skillsRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnSkillById_WhenFound() {
        // Arrange
        Long id = 1L;
        Skill mockSkill = new Skill();
        when(skillsRepository.findById(id)).thenReturn(Optional.of(mockSkill));

        // Act
        Optional<Skill> skill = skillsService.findById(id);

        // Assert
        assertTrue(skill.isPresent());
        assertEquals(mockSkill, skill.get());
        verify(skillsRepository).findById(id); // Por default times es 1
    }

    @Test
    void shouldSaveValidSkill() {
        // Arrange
        Skill validSkill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        when(skillsRepository.save(any(Skill.class))).thenReturn(validSkill);
        doNothing().when(validator).validate(any(Skill.class), any(BindingResult.class));

        // Act
        Skill savedSkill = skillsService.save(validSkill);

        // Assert
        assertNotNull(savedSkill);
        verify(skillsRepository).save(validSkill);
    }
}