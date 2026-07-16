package com.reto_tecnico2.api_comercios.repository;

import com.reto_tecnico2.api_comercios.models.Sede;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SedeRepository extends JpaRepository<Sede, Long> {
    Page<Sede> findByComercio_NombreContainingIgnoreCaseAndEstadoTrue(String nombre, Pageable pageable);
}
