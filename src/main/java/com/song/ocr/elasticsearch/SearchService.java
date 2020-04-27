package com.song.ocr.elasticsearch;

public interface SearchService {
    SearchResults search(String index, String field, String query);
}
