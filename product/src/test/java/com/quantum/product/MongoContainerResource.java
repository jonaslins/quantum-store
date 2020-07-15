package com.quantum.product;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MongoDBContainer;

import java.util.Collections;
import java.util.Map;

public class MongoContainerResource implements QuarkusTestResourceLifecycleManager {

    private final MongoDBContainer mongo = new MongoDBContainer("mongo:4.2.6");

    @Override
    public Map<String, String> start() {
        mongo.start();
        return Collections.singletonMap("quarkus.mongodb.connection-string", mongo.getReplicaSetUrl());
    }

    @Override
    public void stop() {
        mongo.close();
    }
}