package com.zfang.appdemo.refact.price;

import com.zfang.appdemo.refact.Movie;

public class NewReleasePrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
}
