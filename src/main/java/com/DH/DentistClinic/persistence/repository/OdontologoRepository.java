package com.DH.DentistClinic.persistence.repository;
import com.DH.DentistClinic.persistence.entities.Odontologo;
import org.apache.coyote.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
    boolean existsByMatricula(Integer matricula);

    Optional<Odontologo> findByMatricula(Integer matricula);
}
