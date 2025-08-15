package com.aluracursos.literatura_challenge.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate fechaDeceso;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = convertirAnio(datosAutor.fechaNacimiento());
        this.fechaDeceso = convertirAnio(datosAutor.fechaDeceso());
    }

    @Override
    public String toString() {
        return "Autor: " + '\n' +
                ", nombre='" + nombre + '\'' +
                ", año de nacimiento=" + (fechaNacimiento != null ? fechaNacimiento.getYear() : "N/A") + '\'' +
                ", año de deceso=" + (fechaDeceso != null ? fechaDeceso.getYear() : "N/A");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaDeceso() {
        return fechaDeceso;
    }

    public void setFechaDeceso(LocalDate fechaDeceso) {
        this.fechaDeceso = fechaDeceso;
    }

    private LocalDate convertirAnio(String anioString) {
        try {
            int anio = Integer.parseInt(anioString);
            return LocalDate.of(anio, 1, 1);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
