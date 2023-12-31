package com.learnjava.parallelstreams;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.learnjava.util.LoggerUtil.log;

/**
 * @author kansanja on 21/12/23.
 */
public class ParallelStreamResultOrder {

    public static void main(String[] args) {

        /*List<Integer> inputList = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        log("input list: " + inputList);
        List<Integer> resultList = listOrder(inputList);
        log("result list: " + resultList);*/

        Set<Integer> inputList = Set.of(1, 2, 3, 4, 5, 6, 7, 8);
        log("input list: " + inputList);
        Set<Integer> resultList = setOrder(inputList);
        log("result list: " + resultList);

    }

    public static List<Integer> listOrder(List<Integer> inputList) {
        return inputList.parallelStream()
                .map(integer -> integer * 2)
                .collect(Collectors.toList());
    }

    public static Set<Integer> setOrder(Set<Integer> inputList) {
        return inputList.parallelStream()
                .map(integer -> integer * 2)
                .collect(Collectors.toSet());
    }
}
