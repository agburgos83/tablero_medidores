
package com.tablero_medidores.backend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tablero_medidores.backend.model.Operario;

public interface OperarioRepository extends JpaRepository<Operario, Long> {


}