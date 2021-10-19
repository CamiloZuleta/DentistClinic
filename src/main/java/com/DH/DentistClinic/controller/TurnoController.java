package com.DH.DentistClinic.controller;

import com.DH.DentistClinic.Dto.TurnoDto;
import com.DH.DentistClinic.persistence.entities.Turno;
import com.DH.DentistClinic.service.TurnoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private TurnoServiceImpl turnoService;

    @Autowired
    private ObjectMapper mapper;

    private static final Logger logger = LogManager.getLogger(TurnoController.class);

    @PostMapping(path = "/agregar")
    public boolean agregarTurno(@RequestBody TurnoDto turno){
        return turnoService.agregar(turno);
    }

    @DeleteMapping(path ="/borrar")
    public boolean borrarTurno(Turno turno){
        return turnoService.eliminarById(turno.getId());
    }

    @PostMapping(path = "/actualizar")
    public TurnoDto actualizarTurno(@RequestBody Turno turno){
        try{
            return mapper.convertValue(turnoService.actualizar(turno), TurnoDto.class);
        }catch(Exception ex){
            logger.debug(ex);
            return new TurnoDto(null, null, null);
        }
    }

    @GetMapping(path = "/buscar")
    public ArrayList<Turno> buscarTodos(){
        return turnoService.buscarTodos();
    }

    @GetMapping(path = "/buscarSiguiente")
    public ArrayList<Turno> buscarSiguienteSemana(){
        return turnoService.buscarTurnosSiguienteSemana();
    }
}
