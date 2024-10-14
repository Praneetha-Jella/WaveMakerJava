package com.wavemaker;

import com.wavemaker.file.FileContent;
import com.wavemaker.file.FileHandling;
import com.wavemaker.file.WordLocator;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlingTest {

    private static final String TEST_FILE_PATH = "testFile.txt";
    private static final String TEST_DIRECTORY_PATH = "testDir";

    @BeforeEach
    public void setUp() throws IOException {
        // Create a test directory
        Files.createDirectories(Paths.get(TEST_DIRECTORY_PATH));
        // Create a test file with some content
        FileHandling.writeToFile("In a small village, there lived a wise old man.", TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up test files and directories after each test
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
        Files.deleteIfExists(Paths.get(TEST_DIRECTORY_PATH + "/sample.txt"));
        Files.deleteIfExists(Paths.get(TEST_DIRECTORY_PATH));
    }

    @Test
    public void testWriteToFile_ValidInput() throws IOException {
        String content = "Hello, World!";
        FileHandling.writeToFile(content, "testWrite.txt");
        assertTrue(Files.exists(Paths.get("testWrite.txt")));
        assertEquals(content + System.lineSeparator(), Files.readString(Paths.get("testWrite.txt")));
    }

    @Test
    public void testWriteToFile_NullContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileHandling.writeToFile(null, "test.txt");
        });
    }

    @Test
    public void testWriteToFile_EmptyContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileHandling.writeToFile("", "test.txt");
        });
    }

    @Test
    public void testWriteToFile_NullFilePath() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileHandling.writeToFile("Content", null);
        });
    }

    @Test
    public void testReadFromFile_ValidFilePath() throws IOException {
        FileContent content = FileHandling.readFromFile(TEST_FILE_PATH);
        assertEquals(1, content.getLineCount());
        assertEquals("In a small village, there lived a wise old man.", content.getContent().trim());
    }

    @Test
    public void testReadFromFile_NullFilePath() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileHandling.readFromFile(null);
        });
    }

    @Test
    public void testReadFromFile_FileDoesNotExist() {
        assertThrows(FileNotFoundException.class, () -> {
            FileHandling.readFromFile("nonExistentFile.txt");
        });
    }

    @Test
    public void testGetCharacterCount_Valid() {
        int count = FileHandling.getCharacterCount('o', "Hello, World!");
        assertEquals(2, count);
    }

    @Test
    public void testGetCharacterCount_NullContent() {
        assertThrows(NullPointerException.class, () -> {
            FileHandling.getCharacterCount('o', null);
        });
    }

    @Test
    public void testGetCharacterCount_EmptyContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileHandling.getCharacterCount('o', "");
        });
    }

    @Test
    public void testGetWordCount_Valid() {
        int count = FileHandling.getWordCount("a", "A small village, a wise old man.", true);
        assertEquals(2, count);
    }

    @Test
    public void testGetWordCount_NullWord() {
        assertThrows(NullPointerException.class, () -> {
            FileHandling.getWordCount(null, "Hello", true);
        });
    }

    @Test
    public void testGetWordCount_EmptyWord() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileHandling.getWordCount("", "Hello", true);
        });
    }

    @Test
    public void testFindWordWithLineAndIndex_Valid() {
        List<WordLocator> locators = FileHandling.findWordWithLineAndIndex("old", "In a small village, there lived a wise old man.");
        assertEquals(1, locators.size());
        assertEquals(1, locators.get(0).getLineNumber());
        assertEquals(36, locators.get(0).getPosition());
    }

    @Test
    public void testFindWordWithLineAndIndex_NullWord() {
        assertThrows(NullPointerException.class, () -> {
            FileHandling.findWordWithLineAndIndex(null, "Hello");
        });
    }

    @Test
    public void testFindWordInDirectory_ValidDirectory() throws IOException {
        FileHandling.writeToFile("Sample content with the word test.", TEST_DIRECTORY_PATH + "/sample.txt");
        List<String> results = FileHandling.findWordInDirectory("test", TEST_DIRECTORY_PATH, false);
        assertFalse(results.isEmpty());
        assertEquals("testDir/sample.txt - Count: 1", results.get(0));
    }

    @Test
    public void testFindWordInDirectory_InvalidDirectory() {
        assertThrows(IllegalArgumentException.class, () -> {
            FileHandling.findWordInDirectory("test", "invalidDir", false);
        });
    }

    @Test
    public void testPrintWordLocations_EmptyList() {
        // Capture the output to verify
        assertEquals("Word not found.\n", captureOutput(() -> {
            FileHandling.printWordLocations(List.of());
        }));
    }

    private String captureOutput(Runnable runnable) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        runnable.run();
        System.setOut(originalOut);
        return outContent.toString();
    }

}
