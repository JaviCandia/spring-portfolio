package com.javiersillo.portfolio.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    private Long id;

    @NotBlank(message = "El degree no puede venir vacío")
    private String degree;

    @NotBlank(message = "La institución no puede venir vacia")
    private String institution;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha de inicio no puede ser en el futuro")
    private LocalDate startDate;

    @PastOrPresent(message = "La fecha de fin no puede ser en el futuro")
    private LocalDate endDate;

    @NotBlank(message = "La descripción no puede venir vacia")
    private String description;

    // la validación de la clave foránea se hace en el servicio
    private Long personalInfoId;
}
