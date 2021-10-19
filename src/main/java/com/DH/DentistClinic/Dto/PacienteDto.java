package com.DH.DentistClinic.Dto;

import com.DH.DentistClinic.persistence.entities.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaIngreso;
    private Domicilio domicilio;
}
