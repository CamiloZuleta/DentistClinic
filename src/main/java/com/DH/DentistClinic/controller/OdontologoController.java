package com.DH.DentistClinic.controller;

import com.DH.DentistClinic.Dto.OdontologoDto;
import com.DH.DentistClinic.persistence.entities.Odontologo;
import com.DH.DentistClinic.service.OdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    OdontologoService odontologoService;

    @Autowired
    ObjectMapper mapper;

    private static final Logger logger = LogManager.getLogger(OdontologoController.class);

    @PostMapping(path = "/new")
    public boolean nuevoOdontologo(@RequestBody Odontologo odontologo) {
        boolean value = odontologoService.agregar(odontologo);
        if (!value) {
            logger.debug("No se creo la el nuevo Odontologo");
        }
        return value;
    }

    @GetMapping("/searchAll")
    @ResponseBody
    public ArrayList<Odontologo> buscarTodosLosPaciente(){
        return odontologoService.buscarTodos();
    }

    @DeleteMapping("/delete/{id}")
    public boolean borrarPaciente(@PathVariable(value="id") Integer idString){
        boolean value = odontologoService.eliminarById(idString);
        if (value != false) {
            logger.debug("No se borró el Odontologo con id: "+idString);
        }
        return value;
    }

    @DeleteMapping("/deleteDni/{matricula}")
    public boolean borrarPacienteporDni(@PathVariable(value="matricula") Integer matricula){
        boolean value = odontologoService.eliminarByMatricula(matricula);
        if (value != false) {
            logger.debug("No se borró el Odontologo con matrícula: "+matricula);
        }
        return value;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public OdontologoDto actualizarPaciente(@RequestBody Odontologo odontologo){
        Odontologo odontologo1 = new Odontologo();
        try {
            odontologo1 = odontologoService.actualizar(odontologo);
        }catch(Exception e){
            logger.debug(e.getMessage());
        }
        return mapper.convertValue(odontologo1, OdontologoDto.class);
    }
}
