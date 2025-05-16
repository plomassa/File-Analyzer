package com.codeforall.online.fileanalyzer;

public class Main {
    public static void main(String[] args) {


    System.out.println("FILE ANALYZER WITH STREAMS!!");


        System.out.println("Number of lines: "+Util.countLinesFile("Pessoa.txt"));

        System.out.println("Number of words: "+Util.countWordsFile("JoseSeabra.txt"));

        System.out.println("Number of non-empty lines: "+Util.countNonEmptyLinesFile("Pessoa.txt"));

        System.out.println("Number of unique words: "+Util.countUniqueWordsFile("JoseSeabra.txt"));

        System.out.println("Number of words selected: "+Util.findAndCountWordFile("JoseSeabra.txt", "Ou"));

        System.out.println("Lines with selected word: "); Util.findLinesWithWord("JoseSeabra.txt", "ir");

        System.out.println("The Average line length: "+ Util.calcAverageLineLength("Pessoa.txt"));

        System.out.println("The Average word length: "+ Util.calcAverageWordLength("JoseSeabra.txt"));

        System.out.println("Find shorter line: "+Util.findShotestLineFile("Pessoa.txt"));

        System.out.println("Find longest line: "+Util.findLongestLineFile("Pessoa.txt"));

        System.out.println("Longest words : ");Util.findLongestWordsFile("Pessoa.txt",10);

        System.out.println("Get the first word with more than n characters: "+Util.findWordWithNCharsFile("Pessoa.txt",10));

        System.out.println("Common words: "+ Util.findCommonWordsFiles("JoseSeabra.txt", "Pessoa.txt"));
    }
}