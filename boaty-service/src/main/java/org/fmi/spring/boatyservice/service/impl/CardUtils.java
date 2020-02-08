package org.fmi.spring.boatyservice.service.impl;

import java.util.stream.Stream;

public final class CardUtils {

    public static final int MIN_DIGITS = 13;
    public static final int MAX_DIGITS = 16;

    private CardUtils() {}

    public static boolean cardNumberIsValid(long number) {
        boolean digitCountIsValid = false;
        int digitCount = getSize(number);
        if (digitCount >= MIN_DIGITS && digitCount <= MAX_DIGITS) {
            digitCountIsValid =  true;
        }
        boolean prefixMatched = Stream.of(4, 5, 37, 37)
            .anyMatch(digit -> prefixMatched(number, digit));
        int evenPlaceSum = sumOfEvenPlace(number);
        int oddPlaceSum = sumOfOddPlace(number);
        boolean ugh = (evenPlaceSum + oddPlaceSum) % 10 == 0;

        return digitCountIsValid && prefixMatched && ugh;
    }

    private static int sumOfEvenPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    private static int getDigit(int number) {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    private static int sumOfOddPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    private static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    private static int getSize(long d) {
        String num = d + "";
        return num.length();
    }

    private static long getPrefix(long number, int k) {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
}
