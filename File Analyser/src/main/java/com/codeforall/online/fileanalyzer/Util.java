package com.codeforall.online.fileanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.nio.file.Files.readString;

public class Util {

    private static final String path = "src/main/resources/";

    //Count lines in a file.
    static int countLinesFile(String fileName) {

        File file = new File(path + fileName);

        if (!file.exists()) {
            System.out.println("FILE NOT FOUND!");
            return -1;
        }
        try {
            Stream<String> lines = Files.lines(file.toPath());
            long count;
            {
                count = lines
                        .count();
            }
            return (int) count;
        } catch (IOException e) {
            System.err.println("Unable to read file");
        }
        return -1;
    }

    //Count words in a file.
    static int countWordsFile(String fileName) {
        File file = new File(path + fileName);
        String text;
        if (!file.exists()) {
            System.out.println("FILE NOT FOUND!");
            return -1;
        }
        try {
            text = readString(Path.of(file.toURI()));

        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.getMessage();
            return -1;
        }
        return (int) Stream.of(text.trim().split("\\s+"))
                .map(String::trim)
                .filter(w-> !w.isEmpty())
                .count();

    }

    //Count non-empty lines in a file.
    static int countNonEmptyLinesFile(String fileName) {

        File file = new File(path + fileName);
        Stream<String> lines;
        if (!file.exists()) {
            System.out.println("FILE NOT FOUND!");
            return -1;
        }
        try {
            lines = Files.lines(file.toPath());
        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.getMessage();
            return -1;
        }
        long count;
        {
            count = lines
                    .filter(w-> !w.isEmpty())
                    .count();
        }
        return (int) count;

    }

    //Count unique words in a file.
    static int countUniqueWordsFile(String fileName) {
        File file = new File(path + fileName);
        String text;
        if (!file.exists()) {
            System.out.println("FILE NOT FOUND!");
            return -1;
        }
        try {
            text = readString(Path.of(file.toURI()));
        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.getMessage();
            return -1;
        }

        return (int) Stream.of(text.trim().split("\\s+"))
                        .map(s->s.toUpperCase())
                        .distinct()
                        .count();

    }

    //Count how many times a specific word is mentioned in a file.*
    static int findAndCountWordFile(String fileName, String word) {
        File file = new File(path + fileName);
        String text;
        if (!file.exists()) {
            System.out.println("FILE NOT FOUND!");
            return -1;
        }
        try {
            text = readString(Path.of(file.toURI()));

        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.getMessage();
            return -1;
        }
        return (int) Stream.of(text.trim().split("\\s+"))
                .map(String::toUpperCase)
                .filter(w-> w.equals(word.toUpperCase()))
                .count();
    }

    //Find lines which contain a specific word.
    static void findLinesWithWord(String fileName, String word) {
        File file = new File(path + fileName);
        String text;
        if (!file.exists()) {
            System.out.println("FILE NOT FOUND!");
            return;
        }
        try {
            text = readString(Path.of(file.toURI()));

        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.getMessage();
            return;
        }
                   Arrays.stream(text.split("\n"))
                           .filter(w-> w.contains(word))
                           .forEach(System.out::println);
    }

    //Calculate the average line length.
    static double calcAverageLineLength(String fileName) {
        File file = new File(path + fileName);
        Stream<String> lines;
        String text;
        if (!file.exists()) {
            System.out.println("FILE NOT FOUND!");
            return -1;
        }
        try {
            text = readString(Path.of(file.toURI()));
            lines = Files.lines(file.toPath());
        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.getMessage();
            return -1;
        }

        int lineNumber = (int)lines
                    .filter(w-> !w.isEmpty())
                    .count();

        int wordCount = (int)Stream.of(text.trim().split("\\s+"))
                            .map(String::length)
                            .count();


      //  System.out.println("wordCount: "+wordCount);
      //  System.out.println("linenumber: "+lineNumber);
        if (lineNumber!= 0){

            return (double) wordCount/lineNumber;
        }else return 0;

    }

    //Calculate the average word length.
    static double calcAverageWordLength(String fileName) {
        File file = new File(path + fileName);
        String text;
        if (!file.exists()) {
            System.out.println("FILE NOT FOUND!");
            return -1;
        }
        try {
            text = readString(Path.of(file.toURI()));

        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.getMessage();
            return -1;
        }
        int sumWordLength = (int) Stream.of(text.trim().split("\\s+"))
                        .map(String::length)
                        .reduce(0, (a,b) -> a+b);

        int wordCount =  (int) Stream.of(text.trim().split("\\s+"))
                .map(String::trim)
                .filter(w-> !w.isEmpty())
                .count();

        if (wordCount==0){
            return 0;
        }else return (double) sumWordLength/wordCount;

    }

    //Find the shortest line in a file.
    static String findShotestLineFile(String fileName) {
        File file = new File(path + fileName);
        Stream<String> lines;
        if (!file.exists()) {

            return "FILE NOT FOUND!";
        }
        try {
            lines = Files.lines(Path.of(file.toURI()));

        } catch (IOException e) {

            e.getMessage();
            return "Unable to read file";
        }
           Optional <String> shortestLine = lines.filter(w-> !w.isEmpty())
                        .min((String w1, String w2) -> w1.length()-w2.length());

           if (shortestLine.isPresent()) {
               return shortestLine.get();
           }else return "NO SHORTEST LINE";
    }

    //Find the longest line in a file.
    static String findLongestLineFile(String fileName) {
        File file = new File(path + fileName);
        Stream<String> lines;
        if (!file.exists()) {

            return "FILE NOT FOUND!";
        }
        try {
            lines = Files.lines(Path.of(file.toURI()));

        } catch (IOException e) {

            e.getMessage();
            return "Unable to read file";
        }

        Optional <String> longestLine = lines.filter(w-> !w.isEmpty())
                .max((String w1, String w2) -> w1.length()-w2.length());

        if (longestLine.isPresent()) {
            return longestLine.get();
        }else return "NO LONGEST LINE";

    }

    //Find the top n longest words in a file.
    static void findLongestWordsFile(String fileName, int n){
        File file = new File(path + fileName);
        String text;
        if (!file.exists()) {
            System.out.println("FILE NOT FOUND!");
            return;
        }
        try {
            text = readString(Path.of(file.toURI()));

        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.getMessage();
            return;
        }

        List<String> longestWords=  Stream.of(text.trim().split("\\s+"))
                 .distinct()
                 .sorted((String w1, String w2) -> w2.length()-w1.length())
                 .limit((int)n)
                 .collect(Collectors.toList());

        System.out.println(longestWords.toString());
    }

    //Get the first word with more than n characters.
    static String findWordWithNCharsFile(String fileName, int n) {
        File file = new File(path + fileName);
        String text;
        if (!file.exists()) {
            return "SISTEM>> File not found";
        }
        try {
            text = readString(Path.of(file.toURI()));

        } catch (IOException e) {
            e.getMessage();
            return "SISTEM>> Unable to read file";
        }

        Optional<String> word =Stream.of(text.trim().split("\\s+"))
                .distinct()
                .filter(w -> w.length() > n)
                .limit(1)
                .findAny();

        if(word.isPresent()){
            String returnWord = word.get();
            return returnWord;
        }else return "SISTEM>> Word not found";


    }

    //Find common words between two files.
    static String findCommonWordsFiles(String firstFileName, String secondFileName) {
        File firstFile = new File(path + firstFileName);
        File secondFile = new File(path + secondFileName);
        String firstText;
        String secondText;
        if (!firstFile.exists() || !secondFile.exists()) {
            return "SISTEM>> File not found";
        }
        try {
            firstText = readString(Path.of(firstFile.toURI()));
            secondText = readString(Path.of(secondFile.toURI()));

        } catch (IOException e) {
            e.getMessage();
            return "SISTEM>> Unable to read file";
        }
        Set<String> firstSet = Stream.of(firstText.trim().split("\\s+"))
                .distinct()
                .map(s->s.toUpperCase())
                .collect(Collectors.toSet());

        Set<String> secondSet = Stream.of(secondText.trim().split("\\s+"))
                .distinct()
                .map(s->s.toUpperCase())
                .collect(Collectors.toSet());

        Set<String> commonWords = firstSet.stream()
                .filter(secondSet::contains)
                .collect(Collectors.toSet());


        if (commonWords.isEmpty()){
            return "SISTEM>> Common words not found";
        }else return commonWords.toString();

    }

}
