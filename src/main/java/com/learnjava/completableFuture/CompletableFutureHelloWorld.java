package com.learnjava.completableFuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

/**
 * @author kansanja on 18/12/23.
 */
public class CompletableFutureHelloWorld {
    private HelloWorldService helloWorldService;

    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(String::toUpperCase);
             /*   .thenAccept((result) -> {
                    log("Result is " + result);
                }).join();*/
    }

    public String helloWorld_multiple_async_calls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());

        String hw = hello.thenCombine(world, (h, w) -> h + w)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return hw;
    }

    public String helloWorld_3_async_calls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi Completable Future";
        });


        String hw = hello
                .thenCombine(world, (h, w) -> h + w)
                .thenCombine(hiCompletableFuture, (prev, curr) -> prev + curr)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return hw;
    }

    public CompletableFuture<String> helloWorld_thenCompose() {
        return CompletableFuture.supplyAsync(helloWorldService::hello)
                .thenCompose(prev -> helloWorldService.worldFuture(prev))
                .thenApply(String::toUpperCase);

    }


    public static void main(String[] args) {


        log("Done");
        //delay(2000);
    }
}
