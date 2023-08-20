package org.lotr.domain.api;

import org.lotr.domain.settings.TheOneSDKBuilder;

public class RemoteServices {
    protected final ApiService apiService;

    public RemoteServices(TheOneSDKBuilder theOneSDKBuilder) {

        apiService = RetrofitClient.getClient(theOneSDKBuilder.getAPIKey(), theOneSDKBuilder.getRateLimiter()).create(ApiService.class);
    }
}
