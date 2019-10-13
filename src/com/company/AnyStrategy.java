package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class AnyStrategy implements SearchingStrategy {

    @Override
    public List<Integer> search(String[] queries, Map<String, List<Integer>> map, int size) {
        List<Integer> list = new ArrayList<>();
        for (String query : queries) {
            if (!map.containsKey(query))
                continue;

            for (Integer i : map.get(query)) {
                if (!list.contains(i)) {
                    list.add(i);
                }
            }
        }
        return list;
    }
}
