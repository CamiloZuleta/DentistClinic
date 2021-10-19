package com.DH.DentistClinic.persistence.repository;

import com.DH.DentistClinic.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
