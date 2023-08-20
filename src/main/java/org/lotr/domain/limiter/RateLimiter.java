package org.lotr.domain.limiter;

import java.util.concurrent.TimeUnit;

/**
 * RateLimiter is a simple utility class for rate limiting requests based on a specified
 * maximum number of requests allowed within a time window.
 */
public class RateLimiter {
    private final int maxRequests;
    private final int timeWindowSeconds;
    private final TimeUnit timeUnit;
    private int requestCount = 0;
    private long lastRequestTime = 0;

    /**
     * Constructs a RateLimiter with the specified rate limit parameters.
     *
     * @param maxRequests       The maximum number of requests allowed within the time window.
     * @param timeWindow        The time window for the rate limit.
     * @param timeUnit          The time unit for the time window (e.g., TimeUnit.SECONDS).
     */
    public RateLimiter(int maxRequests, int timeWindow, TimeUnit timeUnit) {
        this.maxRequests = maxRequests;
        this.timeWindowSeconds = timeWindow;
        this.timeUnit = timeUnit;
    }

    /**
     * Checks whether a new request is allowed based on the rate limit.
     *
     * @return True if the request is allowed within the rate limit, false otherwise.
     */
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - lastRequestTime;

        if (timeElapsed > timeUnit.toMillis(timeWindowSeconds)) {
            // Reset the request count if the time window has passed
            requestCount = 0;
            lastRequestTime = currentTime;
        }

        if (requestCount < maxRequests) {
            // Allow the request to proceed
            requestCount++;
            lastRequestTime = currentTime;
            return true;
        } else {
            // Reject the request if the rate limit is exceeded
            return false;
        }
    }
}