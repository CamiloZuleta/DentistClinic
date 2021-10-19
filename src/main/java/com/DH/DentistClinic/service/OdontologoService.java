package com.DH.DentistClinic.service;

import com.DH.DentistClinic.persistence.entities.Odontologo;

public interface OdontologoService extends Crud<Odontologo>{
    public boolean eliminarByMatricula(Integer matricula);
}
