package com.mynewco.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/people";
    private Connection connection;

    // Lazy Singleton Inizialization Holder
    private Database() {
    }

    private static class RegistryHolder {
        static Database INSTANCE = new Database();
    }

    public static Database getInstance() {
        return RegistryHolder.INSTANCE;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, "root", "Mercogli@no2022M");

    }

    public void close() throws SQLException {
        connection.close();
    }

    ;
}
