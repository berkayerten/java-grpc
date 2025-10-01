package com.tech_thrive_catalyst.grpc_server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionServiceV1;
import java.io.IOException;

public class GrpcServerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8174)
                .addService(new UserServiceImpl(new UserRepository()))
                .addService(ProtoReflectionServiceV1.newInstance())
                .build();

        server.start();
        server.awaitTermination();
	}

}
