package com.pasareladepagos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/pasarela_pagos";
    private static final String USER = "root";
    private static final String PASSWORD = "administrador";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa!");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
        return connection;
    }
}
