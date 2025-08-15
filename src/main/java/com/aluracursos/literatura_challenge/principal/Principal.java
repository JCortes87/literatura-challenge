package com.aluracursos.literatura_challenge.principal;

import com.aluracursos.literatura_challenge.model.Autor;
import com.aluracursos.literatura_challenge.model.Datos;
import com.aluracursos.literatura_challenge.model.DatosLibro;
import com.aluracursos.literatura_challenge.model.Libro;
import com.aluracursos.literatura_challenge.repository.LibroRepository;
import com.aluracursos.literatura_challenge.service.ConvierteDatos;
import com.aluracursos.literatura_challenge.service.GutendexAPI;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private GutendexAPI consumoApi = new GutendexAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository libroRepository;
    private List<Libro> libros;
    private final String URL_BASE = "http://gutendex.com/books/?search=";

    public Principal(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void menuPrincipal() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro
                    2 - Listar todos los libros buscados
                    3 - Listar todos los autores
                    4 - Listar autores por vivos por época
                    
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresPorEpoca();
                    break;

                case 0:
                    System.out.println("Gracias por utilizar nuestros servicios, hasta pronto");
                    break;
            }
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escriba el titulo del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerLibros(URL_BASE + nombreLibro.replace(" ", "+"));
        var datosBusqueda = convierteDatos.obtenerDatos(json, Datos.class);

        return datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró el libro"));
    }

    private void buscarLibro() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);

        boolean validarLibro = libroRepository.findByApiLibroId(libro.getApiLibroId()).isPresent();

        if(!validarLibro) {
            libroRepository.save(libro);
            System.out.println("Libro guardado en la base de datos con éxito");
            System.out.println(libro);
        } else {
            System.out.println("Libro existente en la base de datos");
            System.out.println(libro);
        }

    }

    private void listarLibros() {
        libros = libroRepository.findAll();
        System.out.println("|----------- Listado de libros buscados -----------------|");
        libros.forEach(l ->
                System.out.println(l.getId() + " - Titulo: " + l.getTitulo()));
        System.out.println("|--------------------------------------------------------|");
    }

    private void listarAutores() {
        libros = libroRepository.findAllWithAutor();
        System.out.println("|----------- Listado de autores buscados -----------------|");
        libros.forEach(l ->
                l.getAutores().forEach(a ->
                        System.out.println(a.getId() + " - Nombre: " + a.getNombre())));
        System.out.println("|--------------------------------------------------------|");
    }

    private void listarAutoresPorEpoca() {
        System.out.println("Ingresa por favor los cuatro digitos del año: ");
        int anio = teclado.nextInt();

        LocalDate fechaBusqueda = LocalDate.of(anio, 1, 1);

        List<Autor> autoresVivos = libroRepository.findAutoresVivosEnFecha(fechaBusqueda);

        System.out.println("|----------- Listado de autores vivos en " + anio + "4 -----------------|");
        autoresVivos.forEach(a ->
                System.out.println(a.getId() + " - Nombre: " + a.getNombre()));
        System.out.println("|--------------------------------------------------------|");
    }
}
