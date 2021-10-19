package com.DH.DentistClinic.persistence.entities;

import lombok.Getter;
import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="turno")
@Getter
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column
    private LocalDateTime turnoFecha;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="paciente_id", referencedColumnName = "id",nullable = false)
    private Paciente paciente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="odontologo_id", nullable = false, referencedColumnName = "id")
    private Odontologo odontologo;

    public Turno(LocalDateTime turnoFecha, Paciente paciente, Odontologo odontologo) {
        this.turnoFecha = turnoFecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Turno() {
    }

    public void setTurnoFecha(LocalDateTime turnoFecha) {
        this.turnoFecha = turnoFecha;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

}


