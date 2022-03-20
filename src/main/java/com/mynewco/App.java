package com.mynewco;

import com.mynewco.db.Database;

import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var db= Database.getInstance();
        try {
            db.connect();
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
        }

        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Hello World!");
    }
}
