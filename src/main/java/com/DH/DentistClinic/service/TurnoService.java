package com.DH.DentistClinic.service;

import com.DH.DentistClinic.persistence.entities.Turno;

import java.util.ArrayList;

public interface TurnoService extends Crud<Turno>{
    public ArrayList<Turno> buscarTurnosSiguienteSemana();
}


