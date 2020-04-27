package com.song.ocr.elasticsearch;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SearchResults {

    private long executionTime;
    private List<SearchResult> resultDocument;

    private long total;
    private long returnedCount;
    private long remainCount;
}
