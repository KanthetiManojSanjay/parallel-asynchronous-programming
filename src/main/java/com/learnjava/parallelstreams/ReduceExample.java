package com.learnjava.parallelstreams;

import java.util.List;

/**
 * @author kansanja on 21/12/23.
 */
public class ReduceExample {

    public int reduce_sum_parallel_stream(List<Integer> inputList) {

        Integer sum = inputList.parallelStream()
                .reduce(0, (x, y) -> x + y);
        return sum;
    }

    public int reduce_multiply_parallel_stream(List<Integer> inputList) {

        Integer sum = inputList.parallelStream()
                .reduce(1, (x, y) -> x * y);
        return sum;
    }
}
