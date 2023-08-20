package org.lotr.domain.services;

import io.reactivex.rxjava3.core.Single;
import org.lotr.data.filters.core.FilterData;
import org.lotr.data.movies.Movie;
import org.lotr.domain.api.RemoteServices;
import org.lotr.domain.filters.FilterProcessor;
import org.lotr.domain.settings.TheOneSDKBuilder;

import java.util.ArrayList;
import java.util.List;

public class MoviesService extends RemoteServices {
    private final String MOVIE_ENDPOINT = "movie/";

    public MoviesService(TheOneSDKBuilder theOneSDKBuilder) {
        super(theOneSDKBuilder);
    }

    public Single<List<Movie>> getMovies(FilterData filterData) {
        return apiService.getMovies(MOVIE_ENDPOINT + FilterProcessor.processQueryParameters(filterData))
                .map(movieData -> {
                    if (movieData != null && movieData.getDocs() != null) {
                        return movieData.getDocs();
                    } else {
                        return new ArrayList<>();
                    }
                });
    }

    public Single<Movie> getMovieByID(String movieID, FilterData filterData) {
        return apiService.getMovieByID(MOVIE_ENDPOINT + movieID + "/" + FilterProcessor.processQueryParameters(filterData))
                .map(movieData -> movieData.getDocs().get(0));
    }
}
