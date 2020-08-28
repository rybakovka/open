package com.helpers;

/**
 * Класс для сравнения денежных строк.
 * @author Константин Рыбаков
 */
public class Money implements Comparable<Money> {
    private Integer whole;
    private Integer fractional;

    /**
     * @param s Число с десятичным разделителем Запятая
     */
    public Money(String s) {
        String[] digits = s.split(",");
        whole = Integer.parseInt(digits[0]);
        fractional = Integer.parseInt(digits[1]);
    }

    @Override
    public String toString() {
        return whole + "," + fractional;
    }

    @Override
    public int compareTo(Money money) {
        if (0 == whole.compareTo(money.whole)) {
            return fractional.compareTo(money.fractional);
        } else {
            return whole.compareTo(money.whole);
        }
    }
}

