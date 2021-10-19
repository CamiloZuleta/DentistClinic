package com.DH.DentistClinic.service;

import com.DH.DentistClinic.persistence.entities.Domicilio;
import com.DH.DentistClinic.persistence.entities.Paciente;
import com.DH.DentistClinic.persistence.repository.DomicilioRepository;
import com.DH.DentistClinic.persistence.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class PacienteServiceImpl implements PacienteService{

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Override
    public boolean agregar(Paciente data) {
        if (data != null && data.getDomicilio().getId() != null){
            if(domicilioRepository.existsById(data.getDomicilio().getId())){
                pacienteRepository.save(new Paciente(data.getNombre(), data.getApellido(), data.getDni(), data.getFechaIngreso()));
            }else{
                pacienteRepository.save(data);
            }
        }else{
            pacienteRepository.save(data);
        }
        return pacienteRepository.existsById(data.getId());
    }

    @Override
    public boolean eliminarById(Integer id) {
        pacienteRepository.deleteById(id);
        return !pacienteRepository.findById(id).isPresent();
    }

    @Override
    public ArrayList<Paciente> buscarTodos() {
        ArrayList<Paciente> pacienteList = new ArrayList<>(pacienteRepository.findAll());
        return pacienteList;
    }

    @Override
    public Paciente actualizar(Paciente paciente ) throws Exception{
        if(pacienteRepository.existsById(paciente.getId())){
            pacienteRepository.save(paciente);
        }else{
            throw new Exception("No se pudo actualizar paciente el id del paciente ya existe");
        }
        return paciente;
    }

    @Override
    public boolean eliminarByDni(String dni) {
        ArrayList<Paciente> pacienteList = this.buscarTodos();
        for(Paciente paciente: pacienteList){
            if(paciente.getDni().equals(dni)){
                pacienteRepository.deleteById(paciente.getId());
                return !pacienteRepository.findById(paciente.getId()).isPresent();
            }
        }
        return false;
    }
}

