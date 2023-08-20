package org.lotr.domain.settings;


import org.lotr.domain.limiter.RateLimiter;

/**
 * A builder class for configuring and creating instances of TheOneSDK.
 */
public class TheOneSDKBuilder {

    private String accessToken;
    private RateLimiter rateLimiter;


    /**
     * Builds the instance of TheOneSDK with the provided configuration settings.
     *
     * @return An instance of TheOneSDK configured with the specified settings.
     */
    public TheOneSDKBuilder build() {
        return this;
    }


    /**
     * Sets the access token for authenticating with The One API.
     *
     * @param accessToken The access token to be set.
     * @return This TheOneSDKBuilder instance for method chaining.
     */
    public TheOneSDKBuilder setAPIKey(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }


    /**
     * Gets the access token currently set in this builder.
     *
     * @return The access token.
     */
    public String getAPIKey() {
        return this.accessToken;
    }


    /**
     * Get the rate limiter configured for this SDK builder.
     *
     * @return The rate limiter used for rate limiting API requests.
     */
    public RateLimiter getRateLimiter() {
        return rateLimiter;
    }


    /**
     * Set the rate limiter for this SDK builder.
     *
     * @param rateLimiter The rate limiter to be used for rate limiting API requests.
     * @return This SDK builder instance for method chaining.
     */
    public TheOneSDKBuilder setRateLimiter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
        return this;
    }
}