package com.company;

import java.util.List;
import java.util.Map;

interface SearchingStrategy {

    List<Integer> search(String[] queries, Map<String, List<Integer>> map, int size);
}
