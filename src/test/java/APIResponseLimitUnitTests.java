import org.lotr.TheOneSDK;
import org.lotr.data.filters.core.FilterData;
import org.lotr.domain.settings.TheOneSDKBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class APIResponseLimitUnitTests {

    private static TheOneSDK theOneSDK;
    private static int RESPONSE_LIMIT = 2;

    @BeforeAll
    public static void setUp() {
        TheOneSDKBuilder theOneSDKBuilder = new TheOneSDKBuilder();
        theOneSDKBuilder.setAPIKey("BuXWpDb4Y2tvntDwMiKg");

        theOneSDK = new TheOneSDK(theOneSDKBuilder);
    }

    /**
     * This unit test method checks whether the number of movies returned by the SDK's
     * {@code getMovies} method with a specified limit adheres to the response limit.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void testAPIResponseLimit() throws Exception {
        FilterData filterData = new FilterData();
        filterData.setLimit(RESPONSE_LIMIT);

        assert theOneSDK.getMovies(filterData).blockingGet().size() <= RESPONSE_LIMIT;
    }
}
