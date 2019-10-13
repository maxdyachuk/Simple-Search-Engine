package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class MyFile {

    private String path;
    private String[] strs;
    private Map<String, List<Integer>> map = new HashMap<>();

    MyFile(File file) {
        this.path = file.getPath();
        try (Scanner scanner = new Scanner(file)){
            List<String> strs = new ArrayList<>();
            while (scanner.hasNextLine()) {
                strs.add(scanner.nextLine());
            }
            this.strs = strs.toArray(new String[0]);
            for (String str : strs) {
                String[] parts = str.toLowerCase().split("\\s+");
                for (String part : parts) {
                    if (!map.containsKey(part)) {
                        map.put(part, new ArrayList<>());
                    }
                    int index = strs.indexOf(str);
                    if (!map.get(part).contains(index)) {
                        map.get(part).add(index);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(path + " not found");
        }
    }

    String getPath() {
        return path;
    }

    String[] getStrs() {
        return strs;
    }

    Map<String, List<Integer>> getMap() {
        return map;
    }


}
