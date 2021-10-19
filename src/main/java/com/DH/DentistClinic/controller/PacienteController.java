package com.DH.DentistClinic.controller;

import com.DH.DentistClinic.Dto.PacienteDto;
import com.DH.DentistClinic.persistence.entities.Paciente;
import com.DH.DentistClinic.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ObjectMapper mapper;

    private static final Logger logger = LogManager.getLogger(PacienteController.class);

    @PostMapping(path = "/nuevo")
    public boolean nuevoPaciente(@RequestBody Paciente paciente) {
        if (!pacienteService.agregar(paciente)) {
            logger.debug("Error al crear el paciente");
            return true;
        }
        return false;
    }

    @GetMapping("/buscarTodos")
    @ResponseBody
    public ArrayList<Paciente> buscarTodosLosPaciente(){
        return pacienteService.buscarTodos();
    }

    @DeleteMapping("/delete/{id}")
    public boolean borrarPaciente(@PathVariable(value="id") Integer idString){
        if (!pacienteService.eliminarById(idString)) {
            logger.debug("Error al eliminar el paciente con id: "+idString);
            return false;
        }
        return true;
    }

    @DeleteMapping("/deleteDni/{dni}")
    public boolean borrarPacienteporDni(@PathVariable(value="dni") String dni){
        if (!pacienteService.eliminarByDni(dni)) {
            logger.debug("Error al eliminar el paciente con dni: "+dni);
            return false;
        }
        return true;
    }

    @PutMapping("/Update")
    @ResponseBody
    public PacienteDto actualizarPaciente(@RequestBody Paciente paciente){
        PacienteDto paciente1 = new PacienteDto();
        try{
            paciente1 =  mapper.convertValue(pacienteService.actualizar(paciente), PacienteDto.class);
        }catch(Exception e){
            logger.debug(e.getMessage());
        }
        return paciente1;
    }
}
