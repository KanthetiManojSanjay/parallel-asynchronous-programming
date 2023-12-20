package com.learnjava.apiclient;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author kansanja on 20/12/23.
 */
class MovieClientTest {
    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies")
            .build();

    MovieClient movieClient = new MovieClient(webClient);

    @RepeatedTest(10)
    void retrieveMovie() {

        startTimer();
        //given
        var movieInfoId = 1L;

        //when
        var movie = movieClient.retrieveMovie(movieInfoId);

        timeTaken();

        System.out.println("movie :" + movie);

        //then
        assertNotNull(movie);
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @RepeatedTest(10)
    void retrieveMovies() {

        startTimer();
        //given
        var movieInfoId = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);

        //when
        var movie = movieClient.retrieveMovies(movieInfoId);

        timeTaken();

        System.out.println("movie :" + movie);

        //then
        assertNotNull(movie);
        assert movie.size() == 7;
    }


    @RepeatedTest(10)
    void retrieveMovie_cf() {

        startTimer();
        //given
        var movieInfoId = 1L;

        //when
        var movie = movieClient.retrieveMovie_cf(movieInfoId).join();

        timeTaken();

        System.out.println("movie :" + movie);

        //then
        assertNotNull(movie);
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @RepeatedTest(10)
    void retrieveMovies_cf() {

        startTimer();
        //given
        var movieInfoId = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);

        //when
        var movie = movieClient.retrieveMovies_cf(movieInfoId);

        timeTaken();

        System.out.println("movie :" + movie);

        //then
        assertNotNull(movie);
        assert movie.size() == 7;
    }

    @RepeatedTest(10)
    void retrieveMovies_cf_Allof() {

        startTimer();
        //given
        var movieInfoId = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);

        //when
        var movie = movieClient.retrieveMovies_cf_allOf(movieInfoId);

        timeTaken();

        System.out.println("movie :" + movie);

        //then
        assertNotNull(movie);
        assert movie.size() == 7;
    }
}