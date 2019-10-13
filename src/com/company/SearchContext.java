package com.company;

import java.util.List;
import java.util.Map;

class SearchContext {

    private SearchingStrategy strategy;

    void setStrategy(SearchingStrategy strategy) {
        this.strategy = strategy;
    }

    List<Integer> search(String[] queries, Map<String, List<Integer>> map, int size) {
        return this.strategy.search(queries, map, size);
    }
}
