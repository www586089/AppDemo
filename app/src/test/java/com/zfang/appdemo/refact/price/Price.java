package com.zfang.appdemo.refact.price;

import com.zfang.appdemo.refact.Movie;

public abstract class Price {
    public abstract double getCharge(int daysRented);
    public abstract int getPriceCode();

    public int getFrequentRenterPoints(int daysRented) {
        if ((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
