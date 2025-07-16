package com.pm.billingservice.grpc;


import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// grpc service handled by Spring boot
@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest ,
                                     StreamObserver<billing.BillingResponse> responseObserver) {

        //StreamObserver<billing.BillingResponse> responseObserver
        // when ever we StreamObserver do
        // earn multiple responses to the client and accept back and forth communiication to the client
        //in the real time

        log.info("createBillingAccount request received {}", billingRequest.toString());


        // Business Logic - save to database , perform calculates etc

        BillingResponse billingResponse = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(billingResponse);
        responseObserver.onCompleted();

    }

}
