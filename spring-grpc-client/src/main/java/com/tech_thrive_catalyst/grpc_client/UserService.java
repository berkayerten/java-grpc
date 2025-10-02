package com.tech_thrive_catalyst.grpc_client;

public interface UserService {

    UserDto getUser(String id);

    UserDto addUser(String name, String email);
}
