package com.song.ocr.elasticsearch;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SearchResult {

    private final Map<String, Object> document;

    public SearchResult(Map<String, Object> document) {
        this.document = document;
    }
}
