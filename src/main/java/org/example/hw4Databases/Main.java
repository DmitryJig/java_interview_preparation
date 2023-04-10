package org.example.hw4Databases;

import org.example.hw4Databases.Services.MovieService;
import org.example.hw4Databases.Services.SeanceService;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        DbStructureCreator structureCreator = new DbStructureCreator(connection); // create tables
        MovieService movieService = new MovieService(connection);
        movieService.getAllMovies().forEach(System.out::println);
        SeanceService seanceService = new SeanceService(connection, movieService);
        seanceService.getAllSeances().forEach(System.out::println);
    }
}
