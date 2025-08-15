package com.aluracursos.literatura_challenge.repository;

import com.aluracursos.literatura_challenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByApiLibroId(Long apiLibroId);

}
