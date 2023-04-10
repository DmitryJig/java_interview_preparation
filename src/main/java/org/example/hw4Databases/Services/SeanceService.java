package org.example.hw4Databases.Services;

import org.example.hw4Databases.Movie;
import org.example.hw4Databases.Seance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeanceService {
    Connection connection;
    MovieService movieService;

    public SeanceService(Connection connection, MovieService movieService) {
        this.connection = connection;
        this.movieService = movieService;
    }
    public List<Seance> getAllSeances(){
        List<Seance> seances = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM seances;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Seance seance = new Seance();
                seance.setId(rs.getLong("id"));
                seance.setMovie(movieService.getMovieById(rs.getLong("movie_id"))); // так конечно не очень хорошо дергать бд несколько раз
                seance.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                seance.setPrice(rs.getBigDecimal("price"));
                seances.add(seance);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка поиска продукта в базе данных");
            e.printStackTrace();
        }
        return seances;
    }
}
