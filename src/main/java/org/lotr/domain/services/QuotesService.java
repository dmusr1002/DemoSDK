package org.lotr.domain.services;

import io.reactivex.rxjava3.core.Single;
import org.lotr.data.filters.core.FilterData;
import org.lotr.data.quotes.Quote;
import org.lotr.domain.api.RemoteServices;
import org.lotr.domain.filters.FilterProcessor;
import org.lotr.domain.settings.TheOneSDKBuilder;

import java.util.ArrayList;
import java.util.List;

public class QuotesService extends RemoteServices {
    private final String QUOTES_ENDPOINT = "quote/";
    private final String MOVIES_ENDPOINT = "movie/";

    public QuotesService(TheOneSDKBuilder theOneSDKBuilder) {
        super(theOneSDKBuilder);
    }

    /**
     * only working for the LotR trilogy
     *
     * @param movieID
     * @return
     */

    public Single<List<Quote>> getAllQuotesByMovieID(String movieID, FilterData filterData) {
        return apiService.getAllQuotesByMovieID(MOVIES_ENDPOINT + movieID + "/" + QUOTES_ENDPOINT + FilterProcessor.processQueryParameters(filterData))
                .map(movieData -> {
                    if (movieData != null && movieData.getDocs() != null) {
                        return movieData.getDocs();
                    } else {
                        return new ArrayList<>();
                    }
                });
    }


    public Single<List<Quote>> getQuotes(FilterData filterData) {
        return apiService.getQuotes(QUOTES_ENDPOINT + FilterProcessor.processQueryParameters(filterData))
                .map(movieData -> {
                    if (movieData != null && movieData.getDocs() != null) {
                        return movieData.getDocs();
                    } else {
                        return new ArrayList<>();
                    }
                });
    }

    public Single<Quote> getQuotesByID(String quoteID, FilterData filterData) {
        return apiService.getQuoteByID(QUOTES_ENDPOINT + quoteID + "/" + FilterProcessor.processQueryParameters(filterData))
                .map(quotesResponse -> quotesResponse.getDocs().get(0));
    }
}
