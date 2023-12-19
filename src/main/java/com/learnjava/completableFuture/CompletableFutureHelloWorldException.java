package com.learnjava.completableFuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

/**
 * @author kansanja on 18/12/23.
 */
public class CompletableFutureHelloWorldException {
    private HelloWorldService helloWorldService;

    public CompletableFutureHelloWorldException(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public String helloWorld_3_async_calls_handle() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi Completable Future";
        });


        String hw = hello
                .handle((res, e) -> {
                    log("res is :" + res);
                    if (e != null) {
                        log("Exception is :" + e.getMessage());
                        return "";
                    } else {
                        return res;
                    }
                })
                .thenCombine(world, (h, w) -> h + w)
                .handle((res, e) -> {
                    log("res is :" + res);
                    if (e != null) {
                        log("Exception after world is :" + e.getMessage());
                        return "";
                    } else {
                        return res;
                    }
                })
                .thenCombine(hiCompletableFuture, (prev, curr) -> prev + curr)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return hw;
    }

    public String helloWorld_3_async_calls_exceptionally() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi Completable Future";
        });


        String hw = hello
                .exceptionally((e) -> {
                    log("Exception is :" + e.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h + w)
                .exceptionally((e) -> {
                    log("Exception after world is :" + e.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (prev, curr) -> prev + curr)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return hw;
    }

    public String helloWorld_3_async_calls_whenComplete() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi Completable Future";
        });


        String hw = hello
                .whenComplete((res, e) -> {
                    log("res is :" + res);
                    if (e != null) {
                        log("Exception is :" + e.getMessage());
                    }
                })
                .thenCombine(world, (h, w) -> h + w)
                .whenComplete((res, e) -> {
                    log("res is :" + res);
                    if (e != null) {
                        log("Exception after world is :" + e.getMessage());
                    }
                })
                .exceptionally((e) -> {
                    log("Exception after hello world is :" + e.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (prev, curr) -> prev + curr)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return hw;
    }
}
