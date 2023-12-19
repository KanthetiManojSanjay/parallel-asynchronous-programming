package com.learnjava.completableFuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author kansanja on 18/12/23.
 */
public class CompletableFutureHelloWorldTest {

    HelloWorldService helloWorldService = new HelloWorldService();
    CompletableFutureHelloWorld completableFutureHelloWorld = new CompletableFutureHelloWorld(helloWorldService);


    @Test
    public void helloWorld() {
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld();

        completableFuture.thenAccept(s -> assertEquals("HELLO WORLD", s)).join();
    }


    @Test
    public void helloWorld_multiple_async_calls_Test() {
        String combinedHelloWorld = completableFutureHelloWorld.helloWorld_multiple_async_calls();
        assertEquals("HELLO WORLD!", combinedHelloWorld);
    }

    @Test
    public void helloWorld_3_async_calls_Test() {
        String combinedHelloWorld = completableFutureHelloWorld.helloWorld_3_async_calls();
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE", combinedHelloWorld);
    }

    @Test
    public void helloWorld_thenCompose_Test() {
        startTimer();
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld_thenCompose();
        completableFuture.thenAccept(s -> assertEquals("HELLO WORLD!", s)).join();
        timeTaken();
    }

}