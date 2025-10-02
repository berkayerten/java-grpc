package com.tech_thrive_catalyst.grpc_client;

import com.tech_thrive_catalyst.grpc_client.user.UserRequest;
import com.tech_thrive_catalyst.grpc_client.user.UserResponse;
import com.tech_thrive_catalyst.grpc_client.user.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClientApplication {

	public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8174)
                .usePlaintext()
                .build();
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        UserResponse response = stub.getUser(UserRequest.newBuilder()
                .setId("12345")
                .build());

        System.out.println(response.getName() + " <" + response.getEmail() + ">");

        channel.shutdown();

	}

}
