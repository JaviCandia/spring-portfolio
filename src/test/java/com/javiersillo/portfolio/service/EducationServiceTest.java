package com.javiersillo.portfolio.service;

import com.javiersillo.portfolio.model.Education;
import com.javiersillo.portfolio.repository.EducationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EducationServiceTest {

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private EducationService educationService;

    @Test
    void shouldReturnListOfEducations_WhenDataExists() {
        // Arrange
        List<Education> mockEducations = List.of(new Education(), new Education());
        when(educationRepository.findAll()).thenReturn(mockEducations);

        // Act
        List<Education> educations = educationService.findAll();

        // Assert
        assertNotNull(educations);
        assertEquals(2, educations.size());
        verify(educationRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEducationById_WhenFound() {
        // Arrange
        Long id = 1L;
        Education mockEducation = new Education();
        when(educationRepository.findById(id)).thenReturn(Optional.of(mockEducation));

        // Act
        Optional<Education> education = educationService.findById(id);

        // Assert
        assertTrue(education.isPresent());
        assertEquals(mockEducation, education.get());
        verify(educationRepository).findById(id); // Por default times es 1
    }


    @Test
    void shouldSaveValidEducation() {
        // Arrange
        Education validEducation = new Education(null, "Ingeniería de Software", "Universidad de XYZ",
                LocalDate.of(2015, 3, 1), LocalDate.of(2020, 12,
                15), "Descripción", 1L);

        when(educationRepository.save(any(Education.class))).thenReturn(validEducation);
        doNothing().when(validator).validate(any(Education.class), any(BindingResult.class));

        // Act
        Education savedEducation = educationService.save(validEducation);

        // Assert
        assertNotNull(savedEducation);
        verify(educationRepository).save(validEducation);
    }
}