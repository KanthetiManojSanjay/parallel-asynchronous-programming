package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

/**
 * @author kansanja on 21/12/23.
 */
public class ParallelStreamsExample {
    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform(namesList);
        log("resultList :" + resultList);
        timeTaken();

        List<String> stringToLowerCaseList = parallelStreamsExample.string_toLowerCase(namesList);
        log("resultList :" + stringToLowerCaseList);
    }


    public List<String> stringTransform(List<String> namesList) {
        return namesList
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> stringTransform_1(List<String> namesList, boolean isParallel) {

        Stream<String> namesStream = namesList.stream();
        if (isParallel)
            namesStream.parallel();

        return namesStream
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> string_toLowerCase(List<String> namesList) {
        return namesList
                .parallelStream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }
}
