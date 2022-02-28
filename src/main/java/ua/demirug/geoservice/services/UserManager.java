package ua.demirug.geoservice.services;

import ua.demirug.geoservice.services.datebase.AccountBase;
import ua.demirug.geoservice.user.Location;
import ua.demirug.geoservice.user.User;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class UserManager {

    private static HashMap<UUID, User> userList = new HashMap<>();

    public static User registerUser(UUID uuid) {

        User user = User.builder()
                .uuid(UUID.randomUUID())
                .last_request(new Date())
                .location(Location.builder()
                        .x(-1)
                        .y(-1)
                        .build()
                ).build();

        AccountBase.getInstance().insertUser(user);
        UserManager.userList.put(user.getUuid(), user);

        return user;
    }

    public static User getUser(UUID uuid) {
        User user = UserManager.userList.get(uuid);
        if (user == null) {
            user = AccountBase.getInstance().getUser(uuid.toString());
            if (user != null) {
               UserManager.userList.put(user.getUuid(), user);
            }
        }
        return user;
    }

}
