package com.DH.DentistClinic.service;

import com.DH.DentistClinic.persistence.entities.Paciente;

public interface PacienteService extends Crud<Paciente>{
    public boolean eliminarByDni(String dni);
}
