package com.tech_thrive_catalyst.grpc_client;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserServiceClient userServiceClient;

    public UserServiceImpl(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public String getUser(String id) {
        return userServiceClient.getUserInfo(id);
    }
}
