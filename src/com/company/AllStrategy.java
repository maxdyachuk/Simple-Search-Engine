package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class AllStrategy implements SearchingStrategy {

    @Override
    public List<Integer> search(String[] queries, Map<String, List<Integer>> map, int size) {
        List<Integer> list = new ArrayList<>();
        if (!map.containsKey(queries[0])) {
            return list;
        }
        for (Integer i : map.get(queries[0])) {
            if (!list.contains(i)) {
                list.add(i);
            }
        }
        for (String query : queries) {
            if (!map.containsKey(query)) {
                list.clear();
                break;
            }
            list.removeIf(o -> !map.get(query).contains(o));
//            while(iterator.hasNext()) {
//                if (!map.get(query).contains(iterator.next())) {
//                    iterator.remove();
//                }
//            }
        }
        return list;
    }
}
