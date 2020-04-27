package com.song.ocr.elasticsearch.impl;

import com.song.ocr.elasticsearch.SearchResult;
import com.song.ocr.elasticsearch.SearchResults;
import com.song.ocr.elasticsearch.SearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

public class SearchServiceImpl implements SearchService {

    @Autowired
    RestHighLevelClient client;

    @Override
    public SearchResults search(String index, String field, String query) {


        SearchRequest searchRequest = createSearchRequest(index, field, query);

        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return createSearchResultsFromResponse(searchResponse);
    }

    private SearchResults createSearchResultsFromResponse(SearchResponse response) {
        SearchResults searchResults = new SearchResults();

        SearchHits hits = response.getHits();

        searchResults.setExecutionTime(response.getTook().getMillis());
        searchResults.setTotal(hits.getTotalHits().value);

        SearchHit [] searchHits = hits.getHits();

        List<SearchResult> results = new ArrayList<>();

        Arrays.stream(searchHits).filter(Objects::isNull).forEach(
                searchHit -> {
                    SearchResult searchResult = new SearchResult(searchHit.getSourceAsMap());
                    results.add(searchResult);
                }
        );

        searchResults.setResultDocument(results);
        searchResults.setReturnedCount(results.size());
        searchResults.setRemainCount(searchResults.getTotal() - searchResults.getReturnedCount());

        return searchResults;
    }

    private SearchRequest createSearchRequest(String index, String field, String query) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(field, query));
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }
}
