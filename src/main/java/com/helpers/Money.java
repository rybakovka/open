package com.helpers;

/**
 * Класс для сравнения денежных строк.
 * @author Константин Рыбаков
 */
public class Money implements Comparable<Money> {
    private int whole;
    private int fractional;

    /**
     * @param s Число с десятичным разделителем Запятая
     */
    public Money(String s) {
        String[] digits = s.split(",");
        whole = Integer.parseInt(digits[0]);
        fractional = Integer.parseInt(digits[1]);
    }

    @Override
    public int compareTo(Money money) {
        int diffWhole = this.whole - money.whole;
        int diffFractional = this.fractional - money.fractional;
        return 0 == diffWhole ? diffFractional : diffWhole;
    }
}

