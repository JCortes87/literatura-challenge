package com.aluracursos.literatura_challenge.model;

import java.util.List;

public class Libro {
    private Long id;
    private String titulo;
    private List<DatosAutor> autor;
    private List<String> lenguajes;
    private Double totalDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor();
        this.lenguajes = datosLibro.lenguajes();
        this.totalDescargas = datosLibro.totalDescargas();
    }

    @Override
    public String toString() {
        return "Libro: " +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor + '\'' +
                ", lenguajes=" + lenguajes + '\'' +
                ", totalDescargas=" + totalDescargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DatosAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<DatosAutor> autor) {
        this.autor = autor;
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Double getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Double totalDescargas) {
        this.totalDescargas = totalDescargas;
    }
}
