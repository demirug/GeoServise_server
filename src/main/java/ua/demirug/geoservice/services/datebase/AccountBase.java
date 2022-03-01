package ua.demirug.geoservice.services.datebase;

import ua.demirug.geoservice.Log;
import ua.demirug.geoservice.config.ConfigProvider;
import ua.demirug.geoservice.user.Location;
import ua.demirug.geoservice.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.UUID;

public class AccountBase extends Database {

    private static AccountBase dataBase;

    private AccountBase() {
        this.getConnection();
        Log.info("Database connected successful");
    }

    public void init() {
        this.execute("CREATE TABLE IF NOT EXISTS `geo_users` (`uuid` varchar(36) NOT NULL, `datetime` TEXT, `x` FLOAT DEFAULT -1, `y` FLOAT DEFAULT -1, PRIMARY KEY (`uuid`))");
    }

    public void insertUser(User user) {
        this.execute("INSERT INTO `geo_users` (`uuid`, `datetime`) values ('" + user.getUuid().toString() + "','" + user.getLast_request() +  "')");
    }

    public void updateUser(User user) {
        this.execute("UPDATE `geo_users` SET `datetime` = '" + user.getLast_request() + "', `x`='" + user.getLocation().getX() + "', `y`='" + user.getLocation().getY() + "' WHERE `uuid`='" + user.getUuid().toString() + "'");
    }

    public User getUser(String uuid) {
        try {
            ResultSet result = this.getConnection().createStatement().executeQuery("SELECT * FROM `geo_users` WHERE uuid = '" + uuid + "'");
            if (result.next()) {
                return User.builder()
                        .uuid(UUID.fromString(result.getString("uuid")))
                            .last_request(User.dateFormat.parse(result.getString("datetime")))
                            .location(Location.builder()
                                    .x(result.getFloat("x"))
                                    .y(result.getFloat("y"))
                                    .build()
                            )
                            .build();
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static AccountBase getInstance() {
        if (AccountBase.dataBase == null) {
            AccountBase.dataBase = new AccountBase();
        }
        return AccountBase.dataBase;
    }

    @Override
    public String getPath() {
        return ConfigProvider.getInstance().getString("db-name");
    }
}
