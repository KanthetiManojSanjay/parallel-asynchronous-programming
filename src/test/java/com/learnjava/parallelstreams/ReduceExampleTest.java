package com.learnjava.parallelstreams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kansanja on 21/12/23.
 */
class ReduceExampleTest {

    ReduceExample reduceExample = new ReduceExample();

    @Test
    void reduce_sum_parallel_stream() {

        //given
        List<Integer> inputList = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        //when
        int sum = reduceExample.reduce_sum_parallel_stream(inputList);

        //then
        assertEquals(36, sum);
    }

    @Test
    void reduce_sum_parallel_stream_emptyList() {

        //given
        List<Integer> inputList = new ArrayList<>();

        //when
        int sum = reduceExample.reduce_sum_parallel_stream(inputList);

        //then
        assertEquals(0, sum);
    }


    @Test
    void reduce_multiply_parallel_stream() {

        //given
        List<Integer> inputList = List.of(1, 2, 3, 4);

        //when
        int sum = reduceExample.reduce_multiply_parallel_stream(inputList);

        //then
        assertEquals(24, sum);
    }


    @Test
    void reduce_multiply_parallel_stream_emptyList() {

        //given
        List<Integer> inputList = new ArrayList<>();

        //when
        int sum = reduceExample.reduce_multiply_parallel_stream(inputList);

        //then
        assertEquals(1, sum);
    }
}