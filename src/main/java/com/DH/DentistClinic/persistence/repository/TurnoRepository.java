package com.DH.DentistClinic.persistence.repository;

import com.DH.DentistClinic.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turno,Integer> {

    @Query(value = "SELECT t FROM Turno t WHERE (t.turnoFecha BETWEEN :fistTicket AND :secondTicket) AND t.odontologo.matricula = :matricula")
    Optional<List<Turno>> turnoDisponible(
            @Param("fistTicket") LocalDateTime fistTicket,
            @Param("secondTicket") LocalDateTime secondTicket,
            @Param("matricula") Integer matricula
    );

    @Query(value = "FROM Turno t WHERE t.turnoFecha BETWEEN ?1 AND ?2")
    Optional<List<Turno>> turnosSiguienteSemana(LocalDateTime fistTicket, LocalDateTime secondTicket);

}

