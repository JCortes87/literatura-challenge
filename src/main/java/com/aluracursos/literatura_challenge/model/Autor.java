package com.aluracursos.literatura_challenge.model;

import java.time.LocalDate;

public class Autor {
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate fechaDeceso;

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

    @Override
    public String toString() {
        return "Autor: " +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaDeceso=" + fechaDeceso;
    }
}
