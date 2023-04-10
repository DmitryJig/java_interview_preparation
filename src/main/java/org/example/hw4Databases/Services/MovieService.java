package org.example.hw4Databases.Services;

import org.example.hw4Databases.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    Connection connection;

    public MovieService(Connection connection) {
        this.connection = connection;
    }

    public List<Movie> getAllMovies(){
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM movies;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Movie movie = new Movie();
                movie.setId(rs.getLong("id"));
                movie.setName(rs.getString("movie_name"));
                movie.setLength(rs.getInt("length"));
                movies.add(movie);
            }
        } catch (SQLException e) {

            System.out.println("Ошибка поиска продукта в базе данных");
            e.printStackTrace();
        }
        return movies;
    }

    public Movie getMovieById(Long id){
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM movies WHERE id = ?;")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Movie movie = new Movie();
                movie.setId(rs.getLong("id"));
                movie.setName(rs.getString("movie_name"));
                movie.setLength(rs.getInt("length"));
                return movie;
            }
        } catch (SQLException e) {

            System.out.println("Ошибка поиска фильма в базе данных");
            e.printStackTrace();
        }
        return null;
    }
}
