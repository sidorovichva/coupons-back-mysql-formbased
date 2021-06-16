package com.vs.couponsbackmysqlformbased.utils;

/**
 *the class provides randomly created objects (Coupon, Company, Customer)
 * and log in random existing clients into the system
 */
public class Entries {
    public static StringBuilder randomString(int length) {
        StringBuilder sb = new StringBuilder("");
        for (int j = 0; j < length; j++) {
            switch (MyArrayInt.randomNumber(1, 3)) {
                case 1: {
                    sb.append((char)MyArrayInt.randomNumber(48, 57));
                    break;
                } case 2: {
                    sb.append((char)MyArrayInt.randomNumber(65, 90));
                    break;
                } case 3: {
                    sb.append((char)MyArrayInt.randomNumber(97, 122));
                    break;
                } default: break;
            }
        }
        return sb;
    }

    public static long randomNumber(long min, long max) {
        return (min + (long)(Math.random() * (max - min + 1)));
    }
}
