package com.wavemaker.file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {
    public static void main(String[] args) throws IOException {

        writeToFile("In a small village, there lived a wise old man.\n The villagers often came to the old man seeking advice. \nThe old man was known for his wisdom, and every time the villagers had a problem, they would ask the old man what to do.\n The old man would listen carefully and then share his thoughts. \nHis advice was always simple, yet it was the advice that helped the villagers the most.", "MyFile.txt");
        FileContent fileContent = readFromFile("MyFile.txt");
        System.out.println("The count of Character is " + getCharacterCount('s', fileContent.getContent()));
        System.out.println("The count of lines " + fileContent.getLineCount());
        System.out.println("The count of word/string is " +getWordCount("s ", fileContent.getContent(),true));
        printWordLocations(findWordWithLineAndIndex("old", fileContent.getContent()));
        printWordOccurrences("old man","/home/praneethaj_500371",true);
    }

    public static void writeToFile(String line, String filePath) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("The line to write cannot be null or empty.");
        }
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("The file path cannot be null or empty.");
        }
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(line);
            writer.write(System.lineSeparator());
            writer.close();
            System.out.println("Successfully wrote to the file");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing to the file: " + e.getMessage(), e);
        }
    }

    public static FileContent readFromFile(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("The file at the specified path does not exist: " + filePath);
        }
        if (!file.isFile() || !file.canRead()) {
            throw new IOException("The file cannot be read or is not a valid file: " + filePath);
        }
        StringBuilder content = new StringBuilder();
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator()); // Append each line to the content
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        } catch (IOException e) {
            throw new IOException("An error occurred while reading the file: " + e.getMessage(), e);
        }

        return new FileContent(content.toString(), lineNumber);
    }

    public static int getCharacterCount(char ch, String content) {
        if (content == null) {
            throw new NullPointerException("The content cannot be null.");
        }
        if (content.isEmpty()) {
            throw new IllegalArgumentException("The content cannot be an empty string.");
        }
        int countCharacter = 0;
        for (char c : content.toCharArray()) {
            if (Character.toLowerCase(c) == Character.toLowerCase(ch)) {
                countCharacter++;
            }
        }
        return countCharacter;
    }

    public static int getWordCount(String word, String content, boolean isSubstring) {
        if (word == null || content == null) {
            throw new NullPointerException("Word or content cannot be null.");
        }

        if (word.isEmpty()) {
            throw new IllegalArgumentException("The word to search cannot be an empty string.");
        }
        if (isSubstring) {
            int count = 0;
            int index = content.indexOf(word);
            while (index != -1) {
                count++;
                index = content.indexOf(word, index + word.length()); // Move past the current occurrence
            }
            return count;
        } else {
            String[] words = content.split("[\\s,.'?!]+");
            int wordOccurrence = 0;
            for (String str : words) {
                if (str.equals(word)) {
                    wordOccurrence++;
                }
            }
            return wordOccurrence;
        }
    }

    public static List<WordLocator> findWordWithLineAndIndex(String word, String fileData) {
        if (word == null) {
            throw new NullPointerException("The word to search cannot be null.");
        }
        if (fileData == null) {
            throw new NullPointerException("The file data cannot be null.");
        }
        if (word.isEmpty()) {
            throw new IllegalArgumentException("The word to search cannot be an empty string.");
        }
        if (fileData.isEmpty()) {
            throw new IllegalArgumentException("The file data cannot be an empty string.");
        }
        List<WordLocator> result = new ArrayList<>();
        String[] lines = fileData.split("\\R");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int index = line.indexOf(word);

            while (index != -1) {
                result.add(new WordLocator(i + 1, index));
                index = line.indexOf(word, index + 1);
            }
        }
        return result;
    }

    public static void printWordLocations(List<WordLocator> wordLocations) {
        if (wordLocations.isEmpty()) {
            System.out.println("Word not found.");
        } else {
            for (WordLocator wl : wordLocations) {
                System.out.println(wl);
            }
        }
    }

    public static List<String> findWordInDirectory(String word, String directoryPath, boolean isSubstring) throws IOException {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Search word cannot be null or empty.");
        }

        File directory = new File(directoryPath);

        // Check if directory is valid
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory: " + directoryPath);
        }
        List<String> wordLocations = new ArrayList<>();

        Files.walk(Paths.get(directoryPath))
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try {

                        List<String> lines = Files.readAllLines(file); // Read all lines
                        String content = new String(Files.readAllBytes(file));
                        int count = getWordCount(word, content, isSubstring);

                        if (count > 0) {
                            wordLocations.add(file.toString() + " - Count: " + count);
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + file.toString() + " - " + e.getMessage());
                    }
                });
        return wordLocations;
    }
    public static void printWordOccurrences(String word, String directoryPath, boolean isSubstring) {
        try {
            List<String> result = findWordInDirectory(word, directoryPath, isSubstring);

            if (result.isEmpty()) {
                System.out.println("No occurrences of the word '" + word + "' found in the directory.");
            } else {
                System.out.println("Occurrences of the word '" + word + "':");
                result.forEach(System.out::println);
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

