package com.zfang.appdemo.refact.price;

import com.zfang.appdemo.refact.Movie;


public class RegularPrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.REGUALAR;
    }
}
