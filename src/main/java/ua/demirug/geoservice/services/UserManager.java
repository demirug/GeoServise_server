package ua.demirug.geoservice.services;

import ua.demirug.geoservice.user.User;

import java.util.HashMap;
import java.util.UUID;

public class UserManager {

    private static HashMap<UUID, User> userList = new HashMap<>();

    public static User registerUser(UUID uuid) {

        User user = User.builder()
                .uuid(UUID.randomUUID()).build();

        UserManager.userList.put(user.getUuid(), user);

        return user;
    }

    public static User getUser(UUID uuid) {
        User user = UserManager.userList.get(uuid);
        if (user == null) {
            //TODO SQL Request
        }
        return user;
    }

}
