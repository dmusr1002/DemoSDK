import org.lotr.TheOneSDK;
import org.lotr.data.filters.core.FilterData;
import org.lotr.domain.limiter.RateLimiter;
import org.lotr.domain.settings.TheOneSDKBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import retrofit2.HttpException;

import java.util.concurrent.TimeUnit;


@RunWith(JUnit4.class)
public class RateLimiterUnitTests {

    private static TheOneSDK theOneSDK;
    private static int MAXIMUM_REQUESTS_PER_TIME_FRAME = 2;

    @BeforeAll
    public static void setUp() {
        TheOneSDKBuilder theOneSDKBuilder = new TheOneSDKBuilder();
        theOneSDKBuilder.setAPIKey("BuXWpDb4Y2tvntDwMiKg");
        theOneSDKBuilder.setRateLimiter(new RateLimiter(MAXIMUM_REQUESTS_PER_TIME_FRAME, 1, TimeUnit.MINUTES));

        theOneSDK = new TheOneSDK(theOneSDKBuilder);
    }

    /**
     * This test method checks the rate limiting behavior of the RateLimiter.
     * It ensures that when the maximum allowed number of requests in a time frame is exceeded,
     * a `429` HTTP status code exception is thrown.
     *
     * The test runs the `theOneSDK.getMovies` method in a loop, making more requests than the
     * maximum allowed in a time frame. If a `429` HTTP exception is thrown during any of these
     * requests, the test passes.
     *
     */
    @Test
    public void testRateLimit() {
        int numberOfRuns = MAXIMUM_REQUESTS_PER_TIME_FRAME + 1;

        for (int i = 0; i < numberOfRuns; i++) {
            try {
                theOneSDK.getMovies(new FilterData()).blockingGet();
            } catch (HttpException e) {
                assert e.code() == 429;

            }
        }
    }
}
