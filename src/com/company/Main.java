package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please, enter path to search in:");
        String rootPath = input.nextLine();
//        String rootPath = "Resources";

        boolean isExit = false;
        List<MyFile> files = new ArrayList<>();

        try {
            filesHierarchyToList(rootPath, files);
            if (files.isEmpty()) {
                System.out.println("There are no files!");
                isExit = true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            isExit = true;
        }

        while (!isExit) {
            printMenu();
            switch (input.nextInt()) {
                case 1:
                    input.nextLine();
                    SearchContext searchContext = new SearchContext();
                    System.out.println("Select a matching strategy (ALL, ANY, NONE):");
                    switch (input.nextLine().toLowerCase()) {
                        case "any":
                            searchContext.setStrategy(new AnyStrategy());
                            break;
                        case "all":
                            searchContext.setStrategy(new AllStrategy());
                            break;
                        case "none":
                            searchContext.setStrategy(new NoneStrategy());
                            break;
                        default:
                            System.out.println("Incorrect option! Using \"ANY\" by default.");
                            searchContext.setStrategy(new AnyStrategy());
                            break;
                    }
                    System.out.println("Enter a search query:");
                    String[] queries = input.nextLine().toLowerCase().split("\\s+");
                    boolean isResult = false;
                    for (MyFile file : files) {
                        if (searchInFile(queries, file, searchContext)) {
                            isResult = true;
                        }
                    }
                    if (!isResult) {
                        System.out.println("\nMo results found.");
                    }
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Bye!");
                    isExit = true;
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
        }

    }

    private static void filesHierarchyToList(String path, List<MyFile> list) throws FileNotFoundException {
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] directory = file.listFiles();
                for (File i : directory != null ? directory : new File[0]) {
                    filesHierarchyToList(i.getPath(), list);
                }
            } else if (file.exists()){
                list.add(new MyFile(file));
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    private static boolean searchInFile(String[] queries, MyFile file, SearchContext searchContext) {
        List<Integer> results = searchContext.search(queries, file.getMap(), file.getStrs().length);
        if (!(results.size() == 0)) {
            System.out.println("\n" + file.getPath() + " (" + results.size() + " results):");
            for (Integer result : results) {
                System.out.println(file.getStrs()[result]);
            }
            return true;
        } else {
            return false;
        }
    }

    private static void printMenu() {
        System.out.println("- - - Menu - - -\n" +
                "1. Search\n" +
                "0. Exit");
    }
}
