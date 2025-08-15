package com.aluracursos.literatura_challenge.repository;

import com.aluracursos.literatura_challenge.model.Autor;
import com.aluracursos.literatura_challenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByApiLibroId(Long apiLibroId);

    @Query("SELECT l FROM Libro l JOIN FETCH l.autores")
    List<Libro> findAllWithAutor();

    @Query("SELECT DISTINCT a FROM Libro l " +
            "JOIN l.autores a " +
            "WHERE a.fechaNacimiento <= :fecha " +
            "AND (a.fechaDeceso IS NULL OR a.fechaDeceso >= :fecha)")
    List<Autor> findAutoresVivosEnFecha(LocalDate fecha);


}
