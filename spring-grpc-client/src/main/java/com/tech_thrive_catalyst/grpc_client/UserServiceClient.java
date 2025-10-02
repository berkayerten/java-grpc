package com.tech_thrive_catalyst.grpc_client;

import com.tech_thrive_catalyst.grpc_client.user.AddUserRequest;

public interface UserServiceClient {

    UserDto getUserInfo(String id);

    UserDto addUser(String name, String email);
}
