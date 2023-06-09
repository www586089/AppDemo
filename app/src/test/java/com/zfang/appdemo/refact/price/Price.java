package com.zfang.appdemo.refact.price;

public abstract class Price {
    public abstract double getCharge(int daysRented);
    public abstract int getPriceCode();

    /**
     * 默认行为
     * @param daysRented
     * @return
     */
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
