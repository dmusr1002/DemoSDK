package org.lotr.domain.interceptor;

import okhttp3.*;
import org.lotr.domain.limiter.RateLimiter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * TheOneInterceptor is an OkHttp interceptor responsible for applying rate limiting
 * and authentication to outgoing HTTP requests. It checks whether the rate limit has been
 * exceeded using a RateLimiter and adds an access token to the request headers for
 * authentication.
 */
public class TheOneInterceptor implements Interceptor {
    private RateLimiter rateLimiter;
    private String accessToken;

    /**
     * Constructs a new instance of TheOneInterceptor.
     *
     * @param rateLimiter The rate limiter to enforce rate limits on requests.
     * @param accessToken The access token used for authentication.
     */
    public TheOneInterceptor(RateLimiter rateLimiter, String accessToken) {
        this.rateLimiter = rateLimiter;
        this.accessToken = accessToken;
    }

    /**
     * Intercepts an HTTP request and adds the access token to the request headers.
     * It also checks if the rate limit is exceeded and returns a 429 Too Many Requests
     * response if necessary.
     *
     * @param chain The interceptor chain.
     * @return The HTTP response.
     * @throws IOException If an error occurs during the interception.
     */
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        if (rateLimiter == null || rateLimiter.allowRequest()) {
            // Allow the request to proceed
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer " + accessToken);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        } else {
            // Reject the request if the rate limit is exceeded
            return new Response.Builder()
                    .code(429) // HTTP 429 Too Many Requests status code
                    .message("Rate limit exceeded")
                    .request(chain.request())
                    .body(ResponseBody.create(MediaType.parse("text/plain"), ""))
                    .protocol(Protocol.HTTP_2)
                    .build();
        }
    }
}