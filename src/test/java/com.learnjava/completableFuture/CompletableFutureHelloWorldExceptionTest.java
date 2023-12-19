package com.learnjava.completableFuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author kansanja on 18/12/23.
 */
@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService mockHelloWorldService = mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldException hwcfe;

    @Test
    void helloWorld_3_async_calls_handle() {

        //given
        when(mockHelloWorldService.hello()).thenThrow(new RuntimeException("Exception occurred"));
        when(mockHelloWorldService.world()).thenCallRealMethod();

        //when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        //then
        assertEquals(" WORLD! HI COMPLETABLE FUTURE", result);
    }

    @Test
    void helloWorld_3_async_calls_handle2() {

        //given
        when(mockHelloWorldService.hello()).thenThrow(new RuntimeException("Exception occurred"));
        when(mockHelloWorldService.world()).thenThrow(new RuntimeException("Exception occurred"));

        //when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        //then
        assertEquals(" HI COMPLETABLE FUTURE", result);
    }

    @Test
    void helloWorld_3_async_calls_handle3() {

        //given
        when(mockHelloWorldService.hello()).thenCallRealMethod();
        when(mockHelloWorldService.world()).thenCallRealMethod();

        //when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE", result);
    }

    @Test
    void helloWorld_3_async_calls_exceptionally() {

        //given
        when(mockHelloWorldService.hello()).thenCallRealMethod();
        when(mockHelloWorldService.world()).thenCallRealMethod();

        //when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE", result);
    }

    @Test
    void helloWorld_3_async_calls_exceptionally2() {

        //given
        when(mockHelloWorldService.hello()).thenThrow(new RuntimeException("Exception occurred"));
        when(mockHelloWorldService.world()).thenThrow(new RuntimeException("Exception occurred"));

        //when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        //then
        assertEquals(" HI COMPLETABLE FUTURE", result);
    }

    @Test
    void helloWorld_3_async_calls_whencomplete() {

        //given
        when(mockHelloWorldService.hello()).thenCallRealMethod();
        when(mockHelloWorldService.world()).thenCallRealMethod();

        //when
        String result = hwcfe.helloWorld_3_async_calls_whenComplete();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE", result);
    }

    @Test
    void helloWorld_3_async_calls_whenComplete2() {

        //given
        when(mockHelloWorldService.hello()).thenThrow(new RuntimeException("Exception occurred"));
        when(mockHelloWorldService.world()).thenThrow(new RuntimeException("Exception occurred"));

        //when
        String result = hwcfe.helloWorld_3_async_calls_whenComplete();

        //then
        assertEquals(" HI COMPLETABLE FUTURE", result);
    }
}