package com.vs.couponsbackmysqlformbased.utils;
import java.util.Arrays;

public class MyArrayString {
    public static String[] myNames = {"Adam", "Bob", "Carol", "Daniel", "Eva", "Filipp", "Gorge", "Henry", "Ivan",
            "John", "Kale", "Luisa", "Milly", "Natan", "Olga", "Peter", "Rosa", "Sandra", "Tom", "Victor", "Wong",
            "Angela", "Betty", "Charles", "Debra", "Eric", "Hanna", "Irene", "Jane", "Kate", "Larry", "Michael", "Netta",
            "Olaf", "Patty", "Richard", "Sam", "Terry"};

    public static String[] myLastNames = {"Sparrow", "Bear", "Cat", "Eagle", "Falcon", "Bee", "Hornet", "Horse", "Jackal",
            "Whale", "Shark", "Dog", "Wolf", "Tiger", "Lobster", "Dove", "Lion", "Snake", "Iguana", "Black", "White",
            "Blue", "Red", "Green", "Grey", "Purple", "Orange", "Yellow"};

    public static String[] myAnimals = {"Sparrow", "Bear", "Cat", "Eagle", "Falcon", "Bee", "Hornet", "Horse", "Jackal",
            "Whale", "Shark", "Dog", "Wolf", "Tiger", "Lobster", "Dove", "Lion", "Snake", "Iguana"};

    public static String[] myColors = {"Black", "White", "Blue", "Red", "Green", "Grey", "Purple", "Orange", "Yellow"};

    public static String[] myAddresses = {"Ayalon", "Bar Kokhva", "Kaplan", "Pinsker", "Bograshov", "Bialik", "Hess",
            "King George", "Rashi", "HaYarkon", "Carmelit", "Daniel", "Frug", "Ranak"};

    public static String[] myCompanyNames = {"Teva", "Leumi", "Hapoalim", "Discount", "Mizrahi", "Migdal",
            "FIBI", "Delek", "Apple", "Microsoft", "Google", "IronSource", "Soluto", "Playtica", "Taboola", "Wix", "Facebook", "Intel", "Salesforce",
            " NSO", "Intuit", "Monday", "Panaya", "Fyber", "Rafael", "Nova", "Vonage", "Lightricks",
            "Nice", "Pluristem", "WalkMe", "Moovit", "SimilarWeb", "Stratasys"};

    public static String[] myCouponTitles = {"Love", "Drink", "Eat", "Travel", "Swim", "Fly",
            "Jump", "Hide", "Run", "Draw", "Ride", "Find", "Watch", "Hunt", "Hike", "Build", "Paint"};

    public static String[] myCategoryTitles = {"Restaurant", "Travel", "Food", "Travel", "Swim", "Fly",
            "Jump", "Hide", "Run", "Draw", "Ride", "Find", "Watch", "Hunt", "Hike", "Build", "Paint"};


    private static int len = 10;

    //Initialization w/o values
    public static String[] initialization() {
        return initialization(len);
    }

    public static String[] initialization(int length) {
        String[] array = new String[length];
        return array;
    }

    //Create with random values
    public static String[] createNewRandom() {
        return createNewRandom(len, myNames);
    }

    public static String[] createNewRandom(int length) {
        return createNewRandom(length, myNames);
    }

    public static String randomEntry(String[] source) {
        if (source.length <= 0) return "Addressed empty source";
        return source[randomNumber(source)];
    }

    public static String[] createNewRandom(int length, String[] source) {
        if (length < 0) length = len;

        String[] bufferString = initialization(0);
        int dice;

        String[] array = new String[length];
        for (int i = 0; i < array.length; i++) {
            if (source.length == 0) {
                source = bufferString;
                bufferString = initialization(0);
            }
            dice = randomNumber(source);
            array[i] = source[dice];
            bufferString = append(bufferString, source[dice]);
            source = deleteItemByIndex(dice, source);
        }
        return array;
    }

    private static int randomNumber(String[] source) {
        return (int)(Math.random() * source.length);
    }

    //find specific item
    public static boolean findItemByValue(String entry, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == entry) return true;
        }
        return false;
    }

    //Concatenation
    public static String[] concat(String[] a1, String[] a2) {
        String[] newArray = initialization(a1.length + a2.length);
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
    public static String[] append(String[] array, String entry) {
        String[] newArray = initialization(array.length + 1);

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = (i == newArray.length - 1) ? entry : array[i];
        }

        return newArray;
    }

    //Delete specific index
    public static String[] deleteItemByIndex(int index, String[] array) {
        if (index >= array.length) {
            System.out.println("The array doesn't have such index");
            return array;
        }

        String[] newArray = initialization(array.length - 1);
        int shift = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (i == index) shift += 1;
            newArray[i] = array[i + shift];
        }
        return newArray;
    }

    //Delete specific value
    public static String[] deleteItemByValue(int num, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(num)) return deleteItemByIndex(i, array);
        }
        return array;
    }

    //Delete all instances of a specific value
    public static String[] deleteItemByValueAll(String entry, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(entry)) return deleteItemByValueAll(entry, deleteItemByIndex(i, array));
        }
        return array;
    }

    //Insert entry to index
    public static String[] insert(String[] array, String entry, int index) {
        String[] newArray = initialization(array.length + 1);
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = (i == index) ? entry : (i > index) ? array[i - 1] : array[i];
        }
        return newArray;
    }

    //Take 2 arrays and create 3rd array that consists only of unique numbers of two of them
    //2 same numbers in one array considered as unique numbers
    public static String[] createUniqueItems2Arrays(String[] a1, String[] a2) {
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a2.length; j++) {
                if (a1[i].equals(a2[j])) {
                    a1 = deleteItemByValueAll(a1[i], a1);
                    a2 = deleteItemByValueAll(a2[j], a2);
                    return createUniqueItems2Arrays(a1, a2);
                }
            }
        }
        return concat(a1,a2);
    }

    //Show
    public static void show(String[] array) {
        System.out.println(Arrays.toString(array));
    }
}
