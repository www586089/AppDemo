package com.zfang.appdemo.refact.price;

import com.zfang.appdemo.refact.Movie;


public class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.REGUALAR;
    }
}
