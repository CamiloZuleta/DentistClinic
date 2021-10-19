package com.DH.DentistClinic.service;

import com.DH.DentistClinic.Dto.TurnoDto;
import com.DH.DentistClinic.persistence.entities.Odontologo;
import com.DH.DentistClinic.persistence.entities.Paciente;
import com.DH.DentistClinic.persistence.entities.Turno;
import com.DH.DentistClinic.persistence.repository.OdontologoRepository;
import com.DH.DentistClinic.persistence.repository.PacienteRepository;
import com.DH.DentistClinic.persistence.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService{
    @Autowired
    TurnoRepository turnoRepository;

    @Autowired
    OdontologoRepository odontologoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger logger = LogManager.getLogger(TurnoServiceImpl.class);

    private boolean estaDisponibleOdontologo(LocalDateTime dateToVeryfy, Integer matricula) {
        //Citas odontológicas de media hora, jornada de 8 am a 5 pm
        LocalDateTime fistTicket = LocalDateTime.of(dateToVeryfy.toLocalDate(), LocalTime.of(8, 0, 0));
        LocalDateTime secondTicket = LocalDateTime.of(dateToVeryfy.toLocalDate(), LocalTime.of(17, 0, 0));
        try {
            Optional<List<Turno>> turnosDelDia = turnoRepository.turnoDisponible(fistTicket, secondTicket, matricula);
            long minutesBetweenDates;

            for (Turno assignedTurn : turnosDelDia.get()) {
                minutesBetweenDates = ChronoUnit.MINUTES.between(assignedTurn.getTurnoFecha(), dateToVeryfy);
                if (!(minutesBetweenDates <= -30 || minutesBetweenDates >= 30)) {
                    return false;
                }
            }
            return true;
        }catch(Exception ex){
            logger.debug(ex);
            return true;
        }
    }

    public boolean agregar(TurnoDto data) {
        if(odontologoRepository.existsByMatricula(data.getOdontologoMatricula())){
            if(estaDisponibleOdontologo(data.getTurnoFecha(), data.getOdontologoMatricula())){
                Odontologo odontologo = odontologoRepository.findByMatricula(data.getOdontologoMatricula()).get();
                Paciente paciente = pacienteRepository.findById(data.getPacienteId()).get();
                turnoRepository.save(new Turno(data.getTurnoFecha(), paciente, odontologo));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean agregar(Turno data) {
        return false;
    }

    @Override
    public boolean eliminarById(Integer id){
        if (turnoRepository.existsById(id)){
            turnoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Turno> buscarTodos() {
        return (ArrayList<Turno>) turnoRepository.findAll();
    }

    @Override
    public Turno actualizar(Turno data) throws Exception {
        if(turnoRepository.existsById(data.getOdontologo().getId())){
            if(estaDisponibleOdontologo(data.getTurnoFecha(), data.getOdontologo().getMatricula())){
                turnoRepository.save(data);
                return data;
            } else {
                throw new Exception("No se pudo crear el turno, intente en un nuevo horario");
            }
        }
        return new Turno(null,null,null);
    }

    @Override
    public ArrayList<Turno> buscarTurnosSiguienteSemana() {
        //Odontologos trabajan de lunes a sábado
        LocalDateTime firstDate = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDateTime secondDate = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        return (ArrayList<Turno>) turnoRepository.turnosSiguienteSemana(firstDate, secondDate).get();
    }
}


