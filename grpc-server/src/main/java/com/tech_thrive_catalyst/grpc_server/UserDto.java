package com.tech_thrive_catalyst.grpc_server;

import java.util.UUID;

public record UserDto(String id, String name, String email) {

    public static UserDto withId(String name, String email) {
        return new UserDto(UUID.randomUUID().toString(), name, email);
    }
}
