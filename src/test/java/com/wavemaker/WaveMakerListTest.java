package com.wavemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.wavemaker.WaveMakerList;

import java.util.Arrays;
import java.util.List;

public class WaveMakerListTest {
    private WaveMakerList<Integer> customList;

    @BeforeEach
    public void setUp() {
        // Set up a new list for each test case
        customList = new WaveMakerList<>();
    }

    @Test
    public void testAddElement() {
        // Test adding a single element
        assertTrue(customList.add(1));
        assertEquals(1, customList.size());
        assertEquals(1, customList.get(0));
    }

    @Test
    public void testAddElementAtIndex() {
        // Test adding element at a specific index
        customList.add(1);
        customList.add(2);
        customList.add(1, 99); // Adding 99 at index 1
        assertEquals(3, customList.size());
        assertEquals(99, customList.get(1));
    }

    @Test
    public void testSize() {
        // Test size of the list
        customList.add(1);
        customList.add(2);
        assertEquals(2, customList.size());
    }

    @Test
    public void testRemoveElementAtIndex() {
        // Test removing an element at a specific index
        customList.add(1);
        customList.add(2);
        customList.add(3);
        customList.remove(1); // Remove element at index 1 (which is 2)
        assertEquals(2, customList.size());
        assertEquals(3, customList.get(1));
    }

    @Test
    public void testRemoveElement() {
        // Test removing a specific element
        customList.add(1);
        customList.add(2);
        assertTrue(customList.remove(Integer.valueOf(2)));
        assertEquals(1, customList.size());
        assertFalse(customList.contains(2));
    }

    @Test
    public void testGetElement() {
        // Test retrieving an element
        customList.add(10);
        customList.add(20);
        assertEquals(20, customList.get(1));
    }

    @Test
    public void testIsEmpty() {
        // Test if list is empty
        assertTrue(customList.isEmpty());
        customList.add(1);
        assertFalse(customList.isEmpty());
    }

    @Test
    public void testContains() {
        // Test if the list contains a specific element
        customList.add(1);
        customList.add(2);
        assertTrue(customList.contains(2));
        assertFalse(customList.contains(3));
    }

    @Test
    public void testSetElement() {
        // Test setting an element at a specific index
        customList.add(1);
        customList.add(2);
        customList.set(1, 99); // Set element at index 1 to 99
        assertEquals(99, customList.get(1));
    }

    @Test
    public void testIndexOf() {
        // Test the indexOf method
        customList.add(10);
        customList.add(20);
        customList.add(30);
        assertEquals(1, customList.indexOf(20));
        assertEquals(0, customList.indexOf(10));
    }

    @Test
    public void testLastIndexOf() {
        // Test the lastIndexOf method
        customList.add(10);
        customList.add(20);
        customList.add(10); // Add 10 again
        assertEquals(2, customList.lastIndexOf(10)); // Last index of 10 should be 2
    }

    @Test
    public void testClear() {
        // Test clearing the list
        customList.add(1);
        customList.add(2);
        customList.clear();
        assertEquals(0, customList.size());
        assertTrue(customList.isEmpty());
    }

    @Test
    public void testAddAll() {
        // Test adding a collection of elements
        customList.addAll(Arrays.asList(1, 2, 3));
        assertEquals(3, customList.size());
        assertTrue(customList.contains(1));
        assertTrue(customList.contains(2));
        assertTrue(customList.contains(3));
    }

    @Test
    public void testContainsAll() {
        // Test containsAll method
        customList.addAll(Arrays.asList(1, 2, 3));
        assertTrue(customList.containsAll(Arrays.asList(1, 2)));
        assertFalse(customList.containsAll(Arrays.asList(1, 4))); // 4 is not present
    }

    @Test
    public void testRemoveAll() {
        // Test removing a collection of elements
        customList.addAll(Arrays.asList(1, 2, 3, 4));
        assertTrue(customList.removeAll(Arrays.asList(2, 3)));
        assertEquals(2, customList.size());
        assertFalse(customList.contains(2));
        assertFalse(customList.contains(3));
    }

    @Test
    public void testSubList() {
        // Test getting a sublist
        customList.addAll(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> subList = customList.subList(1, 3); // Sublist from index 1 to 3
        assertEquals(3, subList.size());
        assertEquals(Arrays.asList(2, 3, 4), subList);
    }

    @Test
    public void testCheckIndexOutOfBounds() {
        // Test for index out of bounds exception
        customList.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            customList.get(5); // This index is out of bounds
        });
    }
}
