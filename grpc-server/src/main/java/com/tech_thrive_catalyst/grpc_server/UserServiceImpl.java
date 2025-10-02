package com.tech_thrive_catalyst.grpc_server;

import com.tech_thrive_catalyst.grpc_server.user.AddUserRequest;
import com.tech_thrive_catalyst.grpc_server.user.UserRequest;
import com.tech_thrive_catalyst.grpc_server.user.UserResponse;
import com.tech_thrive_catalyst.grpc_server.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        repository.findById(request.getId())
                .ifPresent(user -> buildUserResponseObserver(responseObserver, user));
    }

    @Override
    public void addUser(AddUserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserDto user = UserDto.withId(request.getName(), request.getEmail());
        UserDto newUser = repository.save(user);
        buildUserResponseObserver(responseObserver, newUser);
    }

    private static void buildUserResponseObserver(StreamObserver<UserResponse> responseObserver, UserDto user) {
        UserResponse response = toUserResponse(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private static UserResponse toUserResponse(UserDto user) {
        return UserResponse.newBuilder()
                .setId(user.id())
                .setName(user.name())
                .setEmail(user.email())
                .build();
    }
}
