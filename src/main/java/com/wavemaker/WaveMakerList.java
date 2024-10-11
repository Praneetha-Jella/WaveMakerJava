package com.wavemaker;

import java.util.*;

public class WaveMakerList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size = 0;

    public WaveMakerList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public WaveMakerList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid Capacity: " + initialCapacity);
        }
        elements = new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    public boolean add(E e) {
        checkCapacity(size + 1);
        elements[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        checkCapacity(size + 1);
        for (int i = elements.length - 2; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Integer element = (Integer) elements[index];
        Object[] tempElements = new Object[elements.length - index - 1];

        for (int i = 0, j = index + 1; i < tempElements.length && j < elements.length; i++, j++) {
            tempElements[i] = elements[j];
        }

        for (int i = index, j = 0; i < elements.length && j < tempElements.length; i++, j++) {
            elements[i] = tempElements[j];
        }
        size--;
        return (E) element;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
    @Override
    public boolean contains(Object o) {
        boolean flag = false;
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(elements[i], o)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Object o = elements[index];
        elements[index] = element;
        return (E) o;
    }
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(elements[i], o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return 0;
    }
    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = 0;
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(elements[i], o)) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    private void checkCapacity(int minCapacity) {
        if (elements.length - minCapacity < 0) {
            // int oldCapacity = elements.length;
            int newCapacity = minCapacity * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = Arrays.copyOf(elements, elements.length);
        return newArray;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            while (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        checkIndexForSubList(fromIndex,toIndex);
        Object[] subArray = new Object[toIndex - fromIndex + 1];
        for (int i = fromIndex, j = 0; i <= toIndex && j < subArray.length; i++, j++) {
            subArray[j] = elements[i];
        }
        return (List<E>) Arrays.asList(subArray);
    }

    @Override
    public void clear() {
        removeAll(Arrays.asList(elements));
        size = 0;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int newSize = size+c.size();
        checkCapacity(newSize);
        for(E element : c){
            add(element);
        }
        return true;
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int newSize = size+c.size();
        checkCapacity(newSize);
        for(E element : c){
            add(index,element);
        }
        return true;
    }
    @Override
    public boolean retainAll(Collection<?> c) {

        return false;
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index entered: " + index);
        }
    }
    private void checkIndexForSubList(int fromIndex, int toIndex) {
        if(toIndex<fromIndex){
            throw new IndexOutOfBoundsException("To Index cannot be less than from index");
        }
        if(toIndex<0 || toIndex >=size || fromIndex <0 || fromIndex > size){
            throw new IndexOutOfBoundsException("Invalid index entered.");
        }
    }
//    public static void main(String[] args){
//        WaveMakerList<Integer> customList = new WaveMakerList<>();
//        //customList.add(1);
////        customList.add(2);
////        customList.add(2);
////        customList.add(2);
////        customList.add(2);
////        customList.add(2);
////        customList.add(4);
//        System.out.println(customList);
////        System.out.println("Adding 0 at Index 1:");
////        customList.add(1, 0);
////        System.out.println(customList);
////        System.out.println("Getting element at index 2: " + customList.get(2));
////        System.out.println("The list is empty :" + customList.isEmpty());
////
////        customList.set(1, 99);
////        System.out.println("List After set :" + customList);
////        System.out.println("The list contains the element 9: " + customList.contains(9));
////
////        System.out.println("Removing object 2: " + customList.remove(2));
////        System.out.println(customList);
////
////        System.out.println("Index of element 99 is " + customList.indexOf(99));
////        System.out.println("Last index of element 2 is " + customList.lastIndexOf(2));
////        System.out.println("Now the size is: "+customList.size);
////        System.out.println("Adding 0 at Index 1:");
////        customList.add(1,0);
////        System.out.println(customList);
////        System.out.println("Now the size is: "+customList.size);
////
////        System.out.println("Removing element at index 2 which is 0:");
////        customList.remove(2);
////        System.out.println("Now after removal the list is : "+customList);
////        System.out.println("After removal the size is:" +customList.size);
//    }
////    //------------------------------------------------------------------------
   @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }
            @Override
            public E next() {
                return get(currentIndex++);
            }
        };
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

}