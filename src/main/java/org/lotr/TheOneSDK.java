package org.lotr;

import io.reactivex.rxjava3.core.Single;
import org.lotr.data.filters.core.FilterData;
import org.lotr.data.movies.Movie;
import org.lotr.data.quotes.Quote;
import org.lotr.domain.services.MoviesService;
import org.lotr.domain.services.QuotesService;
import org.lotr.domain.settings.TheOneSDKBuilder;

import java.util.List;


/**
 * TheOneSDK is a client library for accessing The One API services.
 * <p>
 * This class provides access to services for retrieving movie information and quotes from The One API.
 * It acts as a central point for making API requests and interacting with the API's endpoints.
 */
public class TheOneSDK {
    private final QuotesService quotesService;

    private final MoviesService moviesService;


    /**
     * Creates an instance of TheOneSDK using the provided builder configuration.
     *
     * @param theOneSDKBuilder The builder configuration for customizing the SDK's behavior.
     */
    public TheOneSDK(TheOneSDKBuilder theOneSDKBuilder) {
        quotesService = new QuotesService(theOneSDKBuilder);
        moviesService = new MoviesService(theOneSDKBuilder);
    }

    /**
     * Get a list of movies based on filter criteria.
     *
     * @param filterData The filter criteria for querying movies.
     * @return A Single emitting a list of movies that match the specified criteria.
     */
    public Single<List<Movie>> getMovies(FilterData filterData) {
        return moviesService.getMovies(filterData);
    }

    /**
     * Get detailed information about a movie by its unique ID.
     *
     * @param movieID The unique identifier of the movie.
     * @param filterData The filter criteria for querying quotes.
     * @return A Single emitting detailed information about the specified movie.
     */
    public Single<Movie> getMovieByID(String movieID, FilterData filterData) {
        return moviesService.getMovieByID(movieID, filterData);
    }


    /**
     * Get a list of quotes based on filter criteria.
     *
     * @param filterData The filter criteria for querying quotes.
     * @return A Single emitting a list of quotes that match the specified criteria.
     */
    public Single<List<Quote>> getQuotes(FilterData filterData) {
        return quotesService.getQuotes(filterData);
    }


    /**
     * Get a list of quotes associated with a specific movie based on filter criteria.
     *
     * @param movieID    The unique identifier of the movie.
     * @param filterData The filter criteria for querying quotes.
     * @return A Single emitting a list of quotes associated with the specified movie and matching the criteria.
     */
    public Single<List<Quote>> getQuotesByMovieID(String movieID, FilterData filterData) {
        return quotesService.getAllQuotesByMovieID(movieID, filterData);
    }

    /**
     * Get detailed information about a quote by its unique ID.
     *
     * @param quoteID    The unique identifier of the quote.
     * @param filterData The filter criteria for querying the quote.
     * @return A Single emitting detailed information about the specified quote.
     */
    public Single<Quote> getQuoteByID(String quoteID, FilterData filterData) {
        return quotesService.getQuotesByID(quoteID, filterData);
    }
}