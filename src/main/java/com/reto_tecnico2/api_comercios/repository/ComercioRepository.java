package com.reto_tecnico2.api_comercios.repository;

import com.reto_tecnico2.api_comercios.models.Comercio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {
    
    // Obtener todos los comercios activos (estado = true)
    List<Comercio> findByEstadoTrue();

    // Obtener un comercio por id siempre que esté activo
    Optional<Comercio> findByIdAndEstadoTrue(Long id);

    @Query("SELECT DISTINCT c FROM Comercio c LEFT JOIN c.sedes s WHERE " +
           "(:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
           "(:distrito IS NULL OR LOWER(s.distrito) LIKE LOWER(CONCAT('%', :distrito, '%'))) AND " +
           "(:estado IS NULL OR c.estado = :estado)")
    Page<Comercio> findByFiltros(@Param("nombre") String nombre, 
                                 @Param("distrito") String distrito, 
                                 @Param("estado") Boolean estado, 
                                 Pageable pageable);
}
