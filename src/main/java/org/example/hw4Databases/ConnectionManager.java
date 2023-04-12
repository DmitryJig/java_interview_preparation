package org.example.hw4Databases;

import lombok.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс соединения с БД, из него получаем connection
 */
@Data
public class ConnectionManager {
    private Connection connection;

    ConnectionManager(){
        try{
            connection = DriverManager.getConnection("jdbc:h2:mem:database;MODE=PostgreSQL",
                    "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException("Error connect DB", e);
        }
    }
}
