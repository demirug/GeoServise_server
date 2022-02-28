package ua.demirug.geoservice.services.datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Database {

    private Connection connection;

    public abstract String getPath();

    public Connection getConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed() && this.connection.isValid(20)) {
                return this.connection;
            }
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.getPath());
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

}