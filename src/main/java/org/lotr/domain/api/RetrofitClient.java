package org.lotr.domain.api;

import okhttp3.OkHttpClient;
import org.lotr.domain.interceptor.TheOneInterceptor;
import org.lotr.domain.limiter.RateLimiter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A utility class for creating a Retrofit client to make API requests.
 */
public class RetrofitClient {
    private static final String BASE_URL = "https://the-one-api.dev/v2/";
    private static Retrofit retrofit;
    private static TheOneInterceptor interceptor;


    /**
     * Creates and configures a Retrofit client for making API requests using OkHttp's retry policy.
     * <a href="https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/-builder/retry-on-connection-failure/">...</a>
     *
     * @param accessToken The access token required for API authentication.
     * @param rateLimiter The rate limiter for rate limiting requests.
     * @return A configured Retrofit client.
     */
    public static Retrofit getClient(final String accessToken, RateLimiter rateLimiter) {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            interceptor = new TheOneInterceptor(rateLimiter, accessToken);
            httpClient.addInterceptor(interceptor);

            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}