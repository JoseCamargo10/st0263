package com.example.p2pnetwork;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.40.1)",
    comments = "Source: peer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class P2PServiceGrpc {

  private P2PServiceGrpc() {}

  public static final String SERVICE_NAME = "P2PService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.GreetingRequest,
      com.example.p2pnetwork.P2PServiceProto.GreetingResponse> getSendGreetingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendGreeting",
      requestType = com.example.p2pnetwork.P2PServiceProto.GreetingRequest.class,
      responseType = com.example.p2pnetwork.P2PServiceProto.GreetingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.GreetingRequest,
      com.example.p2pnetwork.P2PServiceProto.GreetingResponse> getSendGreetingMethod() {
    io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.GreetingRequest, com.example.p2pnetwork.P2PServiceProto.GreetingResponse> getSendGreetingMethod;
    if ((getSendGreetingMethod = P2PServiceGrpc.getSendGreetingMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getSendGreetingMethod = P2PServiceGrpc.getSendGreetingMethod) == null) {
          P2PServiceGrpc.getSendGreetingMethod = getSendGreetingMethod =
              io.grpc.MethodDescriptor.<com.example.p2pnetwork.P2PServiceProto.GreetingRequest, com.example.p2pnetwork.P2PServiceProto.GreetingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendGreeting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.p2pnetwork.P2PServiceProto.GreetingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.p2pnetwork.P2PServiceProto.GreetingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("SendGreeting"))
              .build();
        }
      }
    }
    return getSendGreetingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest,
      com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse> getSendUploadInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendUploadInfo",
      requestType = com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest.class,
      responseType = com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest,
      com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse> getSendUploadInfoMethod() {
    io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest, com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse> getSendUploadInfoMethod;
    if ((getSendUploadInfoMethod = P2PServiceGrpc.getSendUploadInfoMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getSendUploadInfoMethod = P2PServiceGrpc.getSendUploadInfoMethod) == null) {
          P2PServiceGrpc.getSendUploadInfoMethod = getSendUploadInfoMethod =
              io.grpc.MethodDescriptor.<com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest, com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendUploadInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("SendUploadInfo"))
              .build();
        }
      }
    }
    return getSendUploadInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static P2PServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PServiceStub>() {
        @java.lang.Override
        public P2PServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PServiceStub(channel, callOptions);
        }
      };
    return P2PServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static P2PServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PServiceBlockingStub>() {
        @java.lang.Override
        public P2PServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PServiceBlockingStub(channel, callOptions);
        }
      };
    return P2PServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static P2PServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PServiceFutureStub>() {
        @java.lang.Override
        public P2PServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PServiceFutureStub(channel, callOptions);
        }
      };
    return P2PServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class P2PServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendGreeting(com.example.p2pnetwork.P2PServiceProto.GreetingRequest request,
        io.grpc.stub.StreamObserver<com.example.p2pnetwork.P2PServiceProto.GreetingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendGreetingMethod(), responseObserver);
    }

    /**
     */
    public void sendUploadInfo(com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest request,
        io.grpc.stub.StreamObserver<com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendUploadInfoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendGreetingMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.p2pnetwork.P2PServiceProto.GreetingRequest,
                com.example.p2pnetwork.P2PServiceProto.GreetingResponse>(
                  this, METHODID_SEND_GREETING)))
          .addMethod(
            getSendUploadInfoMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest,
                com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse>(
                  this, METHODID_SEND_UPLOAD_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class P2PServiceStub extends io.grpc.stub.AbstractAsyncStub<P2PServiceStub> {
    private P2PServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendGreeting(com.example.p2pnetwork.P2PServiceProto.GreetingRequest request,
        io.grpc.stub.StreamObserver<com.example.p2pnetwork.P2PServiceProto.GreetingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendGreetingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendUploadInfo(com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest request,
        io.grpc.stub.StreamObserver<com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendUploadInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class P2PServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<P2PServiceBlockingStub> {
    private P2PServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.p2pnetwork.P2PServiceProto.GreetingResponse sendGreeting(com.example.p2pnetwork.P2PServiceProto.GreetingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendGreetingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse sendUploadInfo(com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendUploadInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class P2PServiceFutureStub extends io.grpc.stub.AbstractFutureStub<P2PServiceFutureStub> {
    private P2PServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.p2pnetwork.P2PServiceProto.GreetingResponse> sendGreeting(
        com.example.p2pnetwork.P2PServiceProto.GreetingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendGreetingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse> sendUploadInfo(
        com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendUploadInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_GREETING = 0;
  private static final int METHODID_SEND_UPLOAD_INFO = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final P2PServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(P2PServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_GREETING:
          serviceImpl.sendGreeting((com.example.p2pnetwork.P2PServiceProto.GreetingRequest) request,
              (io.grpc.stub.StreamObserver<com.example.p2pnetwork.P2PServiceProto.GreetingResponse>) responseObserver);
          break;
        case METHODID_SEND_UPLOAD_INFO:
          serviceImpl.sendUploadInfo((com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class P2PServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    P2PServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.p2pnetwork.P2PServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("P2PService");
    }
  }

  private static final class P2PServiceFileDescriptorSupplier
      extends P2PServiceBaseDescriptorSupplier {
    P2PServiceFileDescriptorSupplier() {}
  }

  private static final class P2PServiceMethodDescriptorSupplier
      extends P2PServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    P2PServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (P2PServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new P2PServiceFileDescriptorSupplier())
              .addMethod(getSendGreetingMethod())
              .addMethod(getSendUploadInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
