package com.tech_thrive_catalyst.grpc_client;

import com.tech_thrive_catalyst.grpc_client.user.AddUserRequest;
import com.tech_thrive_catalyst.grpc_client.user.UserRequest;
import com.tech_thrive_catalyst.grpc_client.user.UserResponse;
import com.tech_thrive_catalyst.grpc_client.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class UserRemoteService implements UserServiceClient {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub stub;

    @Override
    public UserDto getUserInfo(String id) {
        UserRequest request = UserRequest.newBuilder()
                .setId(id)
                .build();
        UserResponse response = stub.getUser(request);
        return toUserDto(response);
    }

    @Override
    public UserDto addUser(String name, String email) {
        AddUserRequest request = AddUserRequest.newBuilder()
                .setName(name)
                .setEmail(email)
                .build();
        UserResponse response = stub.addUser(request);
        return toUserDto(response);
    }

    private UserDto toUserDto(UserResponse response) {
        return new UserDto(response.getId(), response.getName(), response.getEmail());
    }
}
