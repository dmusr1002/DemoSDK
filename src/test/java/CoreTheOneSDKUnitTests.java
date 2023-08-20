import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lotr.TheOneSDK;
import org.lotr.data.filters.FilterField;
import org.lotr.data.filters.core.FilterContent;
import org.lotr.data.filters.core.FilterData;
import org.lotr.data.filters.core.FilterOption;
import org.lotr.domain.api.ApiService;
import org.lotr.domain.api.RetrofitClient;
import org.lotr.domain.settings.TheOneSDKBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Objects;


/**
 * The purpose of this unit test class, {@code CoreTheOneSDKUnitTests}, is to ensure and guarantee
 * consistency and precision between the functionality provided by the SDK and actual API calls.
 * <p>
 * It contains a set of test methods that compare the results of making direct API requests
 * with the responses obtained by using the SDK's methods for various operations, such as fetching
 * movies by ID, retrieving quotes, and more.
 * <p>
 * These tests help validate that the SDK correctly interacts with the underlying API, ensuring
 * that the data and behavior provided by the SDK aligns with the expected behavior of the API.
 * Any inconsistencies or discrepancies between the two can be identified and addressed through
 * these tests, thereby enhancing the reliability and accuracy of the SDK.
 */
@RunWith(JUnit4.class)
public class CoreTheOneSDKUnitTests {

    private static TheOneSDK theOneSDK;
    private static ApiService apiService;
    private final String RANDOM_MOVIE_ID = "5cd95395de30eff6ebccde57";
    private final String RANDOM_MOVIE_ID_QUOTES = "5cd95395de30eff6ebccde5d";
    private final String RANDOM_QUOTE_ID = "5cd96e05de30eff6ebcce7f0";
    private final String MOVIES_ENDPOINT_TEST = "movie?runtimeInMinutes>=161";
    private final String MOVIE_BY_ID_ENDPOINT_TEST = String.format("%s/%s", "movie", RANDOM_MOVIE_ID);
    private final String QUOTE_BY_ID_ENDPOINT_TEST = String.format("%s/%s", "quote", RANDOM_QUOTE_ID);
    private final String QUOTES_BY_MOVIE_ID_ENDPOINT_TEST = String.format("%s/%s/%s", "movie", RANDOM_MOVIE_ID_QUOTES, "quote");

    private final String QUOTES_ENDPOINT_TEST = "quote?dialog=Arrghh!";

    @BeforeAll
    public static void setUp() {
        TheOneSDKBuilder theOneSDKBuilder = new TheOneSDKBuilder();
        theOneSDKBuilder.setAPIKey("BuXWpDb4Y2tvntDwMiKg");

        theOneSDK = new TheOneSDK(theOneSDKBuilder);
        apiService = RetrofitClient.getClient(theOneSDKBuilder.getAPIKey(), theOneSDKBuilder.getRateLimiter()).create(ApiService.class);
    }


    /**
     * This test compares the results of making a direct API request with using the SDK's
     * {@code getMovies} method with a filter and asserts their equality.
     *
     * @throws JsonProcessingException If JSON serialization encounters an error.
     */
    @Test
    public void testGetMovies() throws JsonProcessingException {
        String directAPIResponse = new ObjectMapper().writeValueAsString(apiService.getMovies(MOVIES_ENDPOINT_TEST).blockingGet().getDocs());

        FilterData filterData = new FilterData();
        filterData.addFilter(new FilterContent(FilterOption.GREATER_THAN_OR_EQUAL_TO, FilterField.RUNTIME_IN_MINUTES, "161"));

        String sdkResponse = new ObjectMapper().writeValueAsString(theOneSDK.getMovies(filterData).blockingGet());

        assert Objects.equals(directAPIResponse, sdkResponse);
    }

    /**
     * This test method compares the results of making a direct API request for quotes with using
     * the SDK's {@code getQuotes} method, applying a specific filter, and asserts their equality.
     *
     * @throws JsonProcessingException If JSON serialization encounters an error.
     */
    @Test
    public void testGetQuotes() throws JsonProcessingException {
        String directAPIResponse = new ObjectMapper().writeValueAsString(apiService.getQuotes(QUOTES_ENDPOINT_TEST).blockingGet().getDocs());

        FilterData filterData = new FilterData();
        filterData.addFilter(new FilterContent(FilterOption.MATCH, FilterField.DIALOG, "Arrghh!"));

        String sdkResponse = new ObjectMapper().writeValueAsString(theOneSDK.getQuotes(filterData).blockingGet());

        assert Objects.equals(directAPIResponse, sdkResponse);
    }

    /**
     * This test method compares the results of making a direct API request to retrieve a movie by ID
     * with using the SDK's {@code getMovieByID} method and asserts their equality.
     *
     * @throws JsonProcessingException If JSON serialization encounters an error.
     */
    @Test
    public void testMovieByID() throws JsonProcessingException {
        String directAPIResponse = new ObjectMapper().writeValueAsString(apiService.getMovieByID(MOVIE_BY_ID_ENDPOINT_TEST).blockingGet().getDocs().get(0));

        FilterData filterData = new FilterData();

        String sdkResponse = new ObjectMapper().writeValueAsString(theOneSDK.getMovieByID(RANDOM_MOVIE_ID, filterData).blockingGet());

        assert Objects.equals(directAPIResponse, sdkResponse);
    }

    /**
     * This test method compares the results of making a direct API request to retrieve a quote by ID
     * with using the SDK's {@code getQuoteByID} method and asserts their equality.
     *
     * @throws JsonProcessingException If JSON serialization encounters an error.
     */
    @Test
    public void testQuoteByID() throws JsonProcessingException {
        String directAPIResponse = new ObjectMapper().writeValueAsString(apiService.getQuoteByID(QUOTE_BY_ID_ENDPOINT_TEST).blockingGet().getDocs().get(0));

        FilterData filterData = new FilterData();

        String sdkResponse = new ObjectMapper().writeValueAsString(theOneSDK.getQuoteByID(RANDOM_QUOTE_ID, filterData).blockingGet());

        assert Objects.equals(directAPIResponse, sdkResponse);
    }

    /**
     * This unit test method compares the results of making a direct API request to retrieve all quotes
     * by movie ID with using the SDK's {@code getQuotesByMovieID} method and asserts their equality.
     *
     * @throws JsonProcessingException If JSON serialization encounters an error.
     */
    @Test
    public void testQuotesByMovieID() throws JsonProcessingException {
        String directAPIResponse = new ObjectMapper().writeValueAsString(apiService.getAllQuotesByMovieID(QUOTES_BY_MOVIE_ID_ENDPOINT_TEST).blockingGet().getDocs());

        FilterData filterData = new FilterData();

        String sdkResponse = new ObjectMapper().writeValueAsString(theOneSDK.getQuotesByMovieID(RANDOM_MOVIE_ID_QUOTES, filterData).blockingGet());

        assert Objects.equals(directAPIResponse, sdkResponse);
    }
}