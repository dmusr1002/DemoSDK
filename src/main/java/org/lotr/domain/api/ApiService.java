package org.lotr.domain.api;

import io.reactivex.rxjava3.core.Single;
import org.lotr.data.movies.MovieData;
import org.lotr.data.quotes.QuotesResponse;
import retrofit2.http.*;

/**
 * An interface defining API endpoints for accessing The One API data.
 */
public interface ApiService {

    @GET
    Single<MovieData> getMovies(@Url String dynamicUrl);

    @GET
    Single<MovieData> getMovieByID(@Url String dynamicUrl);

    @GET
    Single<QuotesResponse> getQuotes(@Url String dynamicUrl);

    @GET
    Single<QuotesResponse> getQuoteByID(@Url String dynamicUrl);

    @GET
    Single<QuotesResponse> getAllQuotesByMovieID(@Url String dynamicUrl);
}
