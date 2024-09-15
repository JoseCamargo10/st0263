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

  private static volatile io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.JoinRequest,
      com.example.p2pnetwork.P2PServiceProto.JoinResponse> getJoinNetworkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JoinNetwork",
      requestType = com.example.p2pnetwork.P2PServiceProto.JoinRequest.class,
      responseType = com.example.p2pnetwork.P2PServiceProto.JoinResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.JoinRequest,
      com.example.p2pnetwork.P2PServiceProto.JoinResponse> getJoinNetworkMethod() {
    io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.JoinRequest, com.example.p2pnetwork.P2PServiceProto.JoinResponse> getJoinNetworkMethod;
    if ((getJoinNetworkMethod = P2PServiceGrpc.getJoinNetworkMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getJoinNetworkMethod = P2PServiceGrpc.getJoinNetworkMethod) == null) {
          P2PServiceGrpc.getJoinNetworkMethod = getJoinNetworkMethod =
              io.grpc.MethodDescriptor.<com.example.p2pnetwork.P2PServiceProto.JoinRequest, com.example.p2pnetwork.P2PServiceProto.JoinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "JoinNetwork"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.p2pnetwork.P2PServiceProto.JoinRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.p2pnetwork.P2PServiceProto.JoinResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("JoinNetwork"))
              .build();
        }
      }
    }
    return getJoinNetworkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.PeerInfo,
      com.google.protobuf.Empty> getUpdateSuccessorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateSuccessor",
      requestType = com.example.p2pnetwork.P2PServiceProto.PeerInfo.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.PeerInfo,
      com.google.protobuf.Empty> getUpdateSuccessorMethod() {
    io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.PeerInfo, com.google.protobuf.Empty> getUpdateSuccessorMethod;
    if ((getUpdateSuccessorMethod = P2PServiceGrpc.getUpdateSuccessorMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getUpdateSuccessorMethod = P2PServiceGrpc.getUpdateSuccessorMethod) == null) {
          P2PServiceGrpc.getUpdateSuccessorMethod = getUpdateSuccessorMethod =
              io.grpc.MethodDescriptor.<com.example.p2pnetwork.P2PServiceProto.PeerInfo, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateSuccessor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.p2pnetwork.P2PServiceProto.PeerInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("UpdateSuccessor"))
              .build();
        }
      }
    }
    return getUpdateSuccessorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.PeerInfo,
      com.google.protobuf.Empty> getUpdatePredecessorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdatePredecessor",
      requestType = com.example.p2pnetwork.P2PServiceProto.PeerInfo.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.PeerInfo,
      com.google.protobuf.Empty> getUpdatePredecessorMethod() {
    io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.PeerInfo, com.google.protobuf.Empty> getUpdatePredecessorMethod;
    if ((getUpdatePredecessorMethod = P2PServiceGrpc.getUpdatePredecessorMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getUpdatePredecessorMethod = P2PServiceGrpc.getUpdatePredecessorMethod) == null) {
          P2PServiceGrpc.getUpdatePredecessorMethod = getUpdatePredecessorMethod =
              io.grpc.MethodDescriptor.<com.example.p2pnetwork.P2PServiceProto.PeerInfo, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdatePredecessor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.p2pnetwork.P2PServiceProto.PeerInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("UpdatePredecessor"))
              .build();
        }
      }
    }
    return getUpdatePredecessorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.LeaveRequest,
      com.google.protobuf.Empty> getNotifyLeaveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NotifyLeave",
      requestType = com.example.p2pnetwork.P2PServiceProto.LeaveRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.LeaveRequest,
      com.google.protobuf.Empty> getNotifyLeaveMethod() {
    io.grpc.MethodDescriptor<com.example.p2pnetwork.P2PServiceProto.LeaveRequest, com.google.protobuf.Empty> getNotifyLeaveMethod;
    if ((getNotifyLeaveMethod = P2PServiceGrpc.getNotifyLeaveMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getNotifyLeaveMethod = P2PServiceGrpc.getNotifyLeaveMethod) == null) {
          P2PServiceGrpc.getNotifyLeaveMethod = getNotifyLeaveMethod =
              io.grpc.MethodDescriptor.<com.example.p2pnetwork.P2PServiceProto.LeaveRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NotifyLeave"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.p2pnetwork.P2PServiceProto.LeaveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("NotifyLeave"))
              .build();
        }
      }
    }
    return getNotifyLeaveMethod;
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
     * <pre>
     * Ask position (predecessor and successor) for a new peer
     * </pre>
     */
    public void joinNetwork(com.example.p2pnetwork.P2PServiceProto.JoinRequest request,
        io.grpc.stub.StreamObserver<com.example.p2pnetwork.P2PServiceProto.JoinResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getJoinNetworkMethod(), responseObserver);
    }

    /**
     * <pre>
     * Notify a peer about its new successor
     * </pre>
     */
    public void updateSuccessor(com.example.p2pnetwork.P2PServiceProto.PeerInfo request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateSuccessorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Notify a peer about its new predecessor
     * </pre>
     */
    public void updatePredecessor(com.example.p2pnetwork.P2PServiceProto.PeerInfo request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdatePredecessorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Notify that a peer is leaving the network
     * </pre>
     */
    public void notifyLeave(com.example.p2pnetwork.P2PServiceProto.LeaveRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getNotifyLeaveMethod(), responseObserver);
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
            getJoinNetworkMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.p2pnetwork.P2PServiceProto.JoinRequest,
                com.example.p2pnetwork.P2PServiceProto.JoinResponse>(
                  this, METHODID_JOIN_NETWORK)))
          .addMethod(
            getUpdateSuccessorMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.p2pnetwork.P2PServiceProto.PeerInfo,
                com.google.protobuf.Empty>(
                  this, METHODID_UPDATE_SUCCESSOR)))
          .addMethod(
            getUpdatePredecessorMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.p2pnetwork.P2PServiceProto.PeerInfo,
                com.google.protobuf.Empty>(
                  this, METHODID_UPDATE_PREDECESSOR)))
          .addMethod(
            getNotifyLeaveMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.p2pnetwork.P2PServiceProto.LeaveRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_NOTIFY_LEAVE)))
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
     * <pre>
     * Ask position (predecessor and successor) for a new peer
     * </pre>
     */
    public void joinNetwork(com.example.p2pnetwork.P2PServiceProto.JoinRequest request,
        io.grpc.stub.StreamObserver<com.example.p2pnetwork.P2PServiceProto.JoinResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getJoinNetworkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Notify a peer about its new successor
     * </pre>
     */
    public void updateSuccessor(com.example.p2pnetwork.P2PServiceProto.PeerInfo request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateSuccessorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Notify a peer about its new predecessor
     * </pre>
     */
    public void updatePredecessor(com.example.p2pnetwork.P2PServiceProto.PeerInfo request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdatePredecessorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Notify that a peer is leaving the network
     * </pre>
     */
    public void notifyLeave(com.example.p2pnetwork.P2PServiceProto.LeaveRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getNotifyLeaveMethod(), getCallOptions()), request, responseObserver);
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
     * <pre>
     * Ask position (predecessor and successor) for a new peer
     * </pre>
     */
    public com.example.p2pnetwork.P2PServiceProto.JoinResponse joinNetwork(com.example.p2pnetwork.P2PServiceProto.JoinRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getJoinNetworkMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Notify a peer about its new successor
     * </pre>
     */
    public com.google.protobuf.Empty updateSuccessor(com.example.p2pnetwork.P2PServiceProto.PeerInfo request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateSuccessorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Notify a peer about its new predecessor
     * </pre>
     */
    public com.google.protobuf.Empty updatePredecessor(com.example.p2pnetwork.P2PServiceProto.PeerInfo request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdatePredecessorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Notify that a peer is leaving the network
     * </pre>
     */
    public com.google.protobuf.Empty notifyLeave(com.example.p2pnetwork.P2PServiceProto.LeaveRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getNotifyLeaveMethod(), getCallOptions(), request);
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
     * <pre>
     * Ask position (predecessor and successor) for a new peer
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.p2pnetwork.P2PServiceProto.JoinResponse> joinNetwork(
        com.example.p2pnetwork.P2PServiceProto.JoinRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getJoinNetworkMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Notify a peer about its new successor
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateSuccessor(
        com.example.p2pnetwork.P2PServiceProto.PeerInfo request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateSuccessorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Notify a peer about its new predecessor
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updatePredecessor(
        com.example.p2pnetwork.P2PServiceProto.PeerInfo request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdatePredecessorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Notify that a peer is leaving the network
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> notifyLeave(
        com.example.p2pnetwork.P2PServiceProto.LeaveRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getNotifyLeaveMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_GREETING = 0;
  private static final int METHODID_JOIN_NETWORK = 1;
  private static final int METHODID_UPDATE_SUCCESSOR = 2;
  private static final int METHODID_UPDATE_PREDECESSOR = 3;
  private static final int METHODID_NOTIFY_LEAVE = 4;

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
        case METHODID_JOIN_NETWORK:
          serviceImpl.joinNetwork((com.example.p2pnetwork.P2PServiceProto.JoinRequest) request,
              (io.grpc.stub.StreamObserver<com.example.p2pnetwork.P2PServiceProto.JoinResponse>) responseObserver);
          break;
        case METHODID_UPDATE_SUCCESSOR:
          serviceImpl.updateSuccessor((com.example.p2pnetwork.P2PServiceProto.PeerInfo) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_PREDECESSOR:
          serviceImpl.updatePredecessor((com.example.p2pnetwork.P2PServiceProto.PeerInfo) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_NOTIFY_LEAVE:
          serviceImpl.notifyLeave((com.example.p2pnetwork.P2PServiceProto.LeaveRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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
              .addMethod(getJoinNetworkMethod())
              .addMethod(getUpdateSuccessorMethod())
              .addMethod(getUpdatePredecessorMethod())
              .addMethod(getNotifyLeaveMethod())
              .build();
        }
      }
    }
    return result;
  }
}
