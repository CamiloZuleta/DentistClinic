package com.DH.DentistClinic.Dto;

import com.DH.DentistClinic.persistence.entities.Odontologo;
import com.DH.DentistClinic.persistence.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private LocalDateTime turnoFecha;
    private Integer pacienteId;
    private Integer odontologoMatricula;

    public TurnoDto(LocalDateTime turnoFecha, Integer paciente, Integer odontologo) {

        this.turnoFecha = turnoFecha;
        this.pacienteId = paciente;
        this.odontologoMatricula = odontologo;
    }
}
