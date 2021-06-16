package com.vs.couponsbackmysqlformbased.utils;
import java.util.Arrays;

public class MyArrayInt {
    private static final int MIN = 1; //including
    private static final int MAX = 10; //including

    private static int len = 10;

    //Initialization w/o values
    public static int[] initialization() {
        return initialization(len);
    }

    public static int[] initialization(int length) {
        int[] array = new int[length];
        return array;
    }

    //Create with random array with values
    public static int[] createNewRandom() {
        return createNewRandom(len, MIN, MAX);
    }

    public static int[] createNewRandom(int length) {
        return createNewRandom(length, MIN, MAX);
    }

    public static int[] createNewRandom(int length, int min, int max) {
        if (length < 0) length = len;
        if (min > max) {
            int buffer = max;
            max = min;
            min = buffer;
        }
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = randomNumber(min, max);
        }
        return array;
    }

    public static int randomNumber(int min, int max) {
        return (min + (int)(Math.random() * (max - min + 1)));
    }

    //find specific item
    public static boolean findItemByValue(int num, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) return true;
        }
        return false;
    }

    //find value that more or less than a value
    public static boolean findItemMoreOrLessThanValue(int num, int value, boolean isAndLower) {
        int factor = (isAndLower) ? 1 : -1;
        if (num * factor < value * factor) return true;
        return false;
    }

    //Concatenation
    public static int[] concat(int[] a1, int[] a2) {
        int[] newArray = initialization(a1.length + a2.length);
        for (int i = 0; i < newArray.length; i++) {
            if (i < a1.length) {
                newArray[i] = a1[i];
            } else {
                newArray[i] = a2[i - a1.length];
            }
        }
        return newArray;
    }

    //Append
    public static int[] append(int[] array, int num) {
        int[] newArray = initialization(array.length + 1);

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = (i == newArray.length - 1) ? num : array[i];
        }

        return newArray;
    }

    //Delete specific index
    public static int[] deleteItemByIndex(int index, int[] array) {
        if (index >= array.length) {
            System.out.println("The array doesn't have such index");
            return array;
        }

        int[] newArray = initialization(array.length - 1);
        int shift = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (i == index) shift += 1;
            newArray[i] = array[i + shift];
        }
        return newArray;
    }

    //Delete specific value
    public static int[] deleteItemByValue(int num, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) return deleteItemByIndex(i, array);
        }
        return array;
    }

    //Delete all instances of a specific value
    public static int[] deleteItemByValueAll(int num, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) return deleteItemByValueAll(num, deleteItemByIndex(i, array));
        }
        return array;
    }

    //Insert value to index
    public static int[] insert(int[] array, int value, int index) {
        int[] newArray = initialization(array.length + 1);
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = (i == index) ? value : (i > index) ? array[i - 1] : array[i];
        }
        return newArray;
    }

    //Take 2 arrays and create 3rd array that consists only of unique numbers of two of them
    //2 same numbers in one array considered as unique numbers
    public static int[] createUniqueItems2Arrays(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a2.length; j++) {
                if (a1[i] == a2[j]) {
                    a1 = deleteItemByValueAll(a1[i], a1);
                    a2 = deleteItemByValueAll(a2[j], a2);
                    return createUniqueItems2Arrays(a1, a2);
                }
            }
        }
        return concat(a1,a2);
    }

    private static boolean isInArray(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) return true;
        }
        return false;
    }

    //Take 2 arrays and create 3rd array that consist of numbers (start = 1, i < two arrays length, i += 1),
    // excluding values which exist in the initial arrays
    public static int[] createUniqueSequentialItems2Arrays(int[] a1, int[] a2) {
        int[] tempArray = concat(a1, a2);
        int[] newArray = initialization(0);
        boolean isInArray;
        int shift = 0;

        for (int i = 1; i <= tempArray.length; i++) {
            do {
                isInArray = isInArray(i + shift, tempArray);
                if (isInArray) shift += 1;
            } while (isInArray);
            newArray = append(newArray, i + shift);
        }
        return newArray;
    }

    //Show
    public static void show(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    //Remove all numbers less or more than a value
    public static int[] removeItemsStartingWithValue(int[] array, int value, boolean isAndLower) {
        for (int i = 0; i < array.length; i++) {
            if (findItemMoreOrLessThanValue(array[i], value, isAndLower)) {
                array = deleteItemByIndex(i, array);
                return removeItemsStartingWithValue(array, value, isAndLower);
            }
        }
        return array;
    }

    public static int[] sort(int[] array, boolean isAscending) {
        int factor = (!isAscending) ? 1 : -1;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i+1] * factor > array[i] * factor) {
                array = append(array, array[i]);
                array = deleteItemByIndex(i, array);
                return sort(array, isAscending);
            }
        }
        return array;
    }

    public static int[] removeDuplicates(int[] array) {
        int counter;
        for (int i = 0; i < array.length; i++) {
            counter = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    counter += 1;
                    if (counter > 1) {
                        array = deleteItemByIndex(Math.max(i, j), array);
                        return removeDuplicates(array);
                    }
                }
            }
        }
        return array;
    }
}
