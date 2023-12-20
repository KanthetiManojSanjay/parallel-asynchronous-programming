package com.learnjava.apiclient;

import com.learnjava.domain.movie.Movie;
import com.learnjava.domain.movie.MovieInfo;
import com.learnjava.domain.movie.Review;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author kansanja on 20/12/23.
 */
public class MovieClient {
    private final WebClient webClient;

    public MovieClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie retrieveMovie(Long movieInfoId) {
        //movieInfo
        var movieInfo = invokeMovieInfoService(movieInfoId);

        //review
        var reviews = invokeReviewsService(movieInfoId);

        return new Movie(movieInfo, reviews);
    }

    public List<Movie> retrieveMovies(List<Long> movieInfoIds) {

        return movieInfoIds.stream()
                .map(this::retrieveMovie)
                .collect(Collectors.toList());
    }

    public CompletableFuture<Movie> retrieveMovie_cf(Long movieInfoId) {
        //movieInfo
        var movieInfo = CompletableFuture.supplyAsync(() -> invokeMovieInfoService(movieInfoId));

        //review
        var reviews = CompletableFuture.supplyAsync(() -> invokeReviewsService(movieInfoId));

        return movieInfo.thenCombine(reviews, Movie::new);

    }

    public List<Movie> retrieveMovies_cf(List<Long> movieInfoIds) {

        var movieFutures = movieInfoIds.stream()
                .map(this::retrieveMovie_cf)
                .collect(Collectors.toList());

        return movieFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<Movie> retrieveMovies_cf_allOf(List<Long> movieInfoIds) {

        var movieFutures = movieInfoIds.stream()
                .map(this::retrieveMovie_cf)
                .collect(Collectors.toList());

        var cfAllOf = CompletableFuture.allOf(movieFutures.toArray(new CompletableFuture[movieFutures.size()]));

        return cfAllOf.thenApply(v ->
                movieFutures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        ).join();
    }

    private MovieInfo invokeMovieInfoService(Long movieInfoId) {
        String moviesInfoUrlPath = "/v1/movie_infos/{movieInfoId}";
        return webClient.get()
                .uri(moviesInfoUrlPath, movieInfoId)
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .block();
    }

    private List<Review> invokeReviewsService(Long movieInfoId) {

        String reviewUri = UriComponentsBuilder.fromUriString("/v1/reviews")
                .queryParam("movieInfoId", movieInfoId)
                .buildAndExpand()
                .toString();

        return webClient.get()
                .uri(reviewUri)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();
    }
}
