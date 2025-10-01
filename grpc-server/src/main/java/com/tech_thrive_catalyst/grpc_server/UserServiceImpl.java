package com.tech_thrive_catalyst.grpc_server;

import com.tech_thrive_catalyst.grpc_server.user.UserRequest;
import com.tech_thrive_catalyst.grpc_server.user.UserResponse;
import com.tech_thrive_catalyst.grpc_server.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        repository.findById(request.getId())
                .ifPresent(user -> getUser(responseObserver, user));
    }

    private static void getUser(StreamObserver<UserResponse> responseObserver, UserDto user) {
        UserResponse response = buildUserResponse(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private static UserResponse buildUserResponse(UserDto user) {
        return UserResponse.newBuilder()
                .setId(user.id())
                .setName(user.name())
                .setEmail(user.email())
                .build();
    }
}
