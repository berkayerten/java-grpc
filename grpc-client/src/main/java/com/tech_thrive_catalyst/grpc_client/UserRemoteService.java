package com.tech_thrive_catalyst.grpc_client;

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
    public String getUserInfo(String id) {
        UserRequest request = UserRequest.newBuilder()
                .setId(id)
                .build();
        UserResponse response = stub.getUser(request);
        return response.getName() + " <" + response.getEmail() + ">";
    }
}
