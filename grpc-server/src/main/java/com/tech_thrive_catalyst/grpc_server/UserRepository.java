package com.tech_thrive_catalyst.grpc_server;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

    private final Map<String, UserDto> users;

    public UserRepository() {
        users = new ConcurrentHashMap<>();
        users.put("12345", new UserDto("12345", "test-user", "test@example.com"));
    }

    public Optional<UserDto> findById(String id) {
        return users.containsKey(id)
            ? Optional.of(users.get(id))
            : Optional.empty();
    }

    public UserDto save(UserDto user) {
        users.put(user.id(), user);
        return user;
    }
}
