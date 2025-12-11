package com.javiersillo.portfolio.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotNull(message = "El porcentaje no puede ser nulo")
    @Min(value = 0, message = "El nivel no puede ser menor a 100")
    @Max(value = 100, message = "El nivel no puede ser mayor a 100")
    private Integer levelPercentage;

    @NotBlank(message = "El icono no puede estar vacío")
    private String iconClass;

    // la validación de la clave foránea se hace en el servicio
    private Long personalInfoId;
}
