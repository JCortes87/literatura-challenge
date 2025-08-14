package com.aluracursos.literatura_challenge.principal;

import com.aluracursos.literatura_challenge.model.Datos;
import com.aluracursos.literatura_challenge.model.DatosLibro;
import com.aluracursos.literatura_challenge.model.Libro;
import com.aluracursos.literatura_challenge.service.ConvierteDatos;
import com.aluracursos.literatura_challenge.service.GutendexAPI;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private GutendexAPI consumoApi = new GutendexAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private final String URL_BASE = "http://gutendex.com/books/?search=";

    public void menuPrincipal() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro
                    
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;

                case 0:
                    System.out.println("Gracias por utilizar nuestros servicios, hasta pronto");
                    break;
            }
        }
    }

    public DatosLibro getDatosLibro() {
        System.out.println("Escriba el titulo del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerLibros(URL_BASE + nombreLibro.replace(" ", "+"));
        var datosBusqueda = convierteDatos.obtenerDatos(json, Datos.class);

        return datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró el libro"));
    }

    public void buscarLibro() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);
        System.out.println(libro);
    }
}
