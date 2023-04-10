package org.example.hw4Databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

/**
 * Создаем таблицы
 */
public class DbStructureCreator {
    Connection connection;

    public DbStructureCreator(Connection connection) {
        this.connection = connection;
        createTableMovies();
        createTableSeances();
        createTableTickets();
        fillTableMovies();
        fillTableSeances();
        fillTableTickets();
    }

    private void createTableMovies() {
        try (final PreparedStatement statement = connection.prepareStatement("" +
                " CREATE TABLE IF NOT EXISTS movies (" +
                " id BIGSERIAL PRIMARY KEY," +
                " movie_name VARCHAR(255) NOT NULL," +
                " length INT NOT NULL);")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableSeances() {
        try (final PreparedStatement statement = connection.prepareStatement("" +
                " CREATE TABLE IF NOT EXISTS seances (" +
                " id BIGSERIAL PRIMARY KEY," +
                " movie_id BIGINT NOT NULL REFERENCES movies (id)," +
                " start_time TIMESTAMP NOT NULL," +
                " price NUMERIC(12, 2) NOT NULL);")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableTickets() {
        try (final PreparedStatement statement = connection.prepareStatement("" +
                " CREATE TABLE IF NOT EXISTS tickets (" +
                " id BIGSERIAL PRIMARY KEY," +
                " seance_id BIGINT NOT NULL REFERENCES seances (id));")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillTableMovies() {
        List<Movie> movies = Arrays.asList(
                new Movie(null, "Titanic", 120),
                new Movie(null, "Fiksiki", 90),
                new Movie(null, "Mumii troll", 60),
                new Movie(null, "Blade", 90),
                new Movie(null, "Taxi", 120)
        );
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO movies (movie_name, length) VALUES (?, ?);")) {
            for (Movie movie : movies) {
                ps.setString(1, movie.getName());
                ps.setString(2, String.valueOf(movie.getLength()));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillTableSeances() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO seances (movie_id, start_time, price) " +
                    "VALUES " +
                    "(1, '2023-05-14 12:00:00', 500), " +
                    "(1, '2023-05-14 13:00:00', 500), " +
                    "(2, '2023-05-14 14:00:00', 500), " +
                    "(5, '2023-05-14 16:00:00', 500), " +
                    "(3, '2023-05-14 19:00:00', 500);");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillTableTickets() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO tickets (seance_id) " +
                    "VALUES " +
                    "(1), " +
                    "(2), " +
                    "(3), " +
                    "(4), " +
                    "(5);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}