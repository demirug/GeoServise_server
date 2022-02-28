package ua.demirug.geoservice.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private static Database dataBase;

    private Database() {
        this.getConnection();
        System.out.println("Database connected successful");
    }

    private Connection connection;

    public Connection getConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed() && this.connection.isValid(20)) {
                return this.connection;
            }
            this.connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.connection;
    }

    public void execute(String query) {
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(query);
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (Database.dataBase == null) {
            Database.dataBase = new Database();
        }
        return Database.dataBase;
    }

}