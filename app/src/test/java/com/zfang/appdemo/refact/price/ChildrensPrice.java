package com.zfang.appdemo.refact.price;

import com.zfang.appdemo.refact.Movie;

public class ChildrensPrice extends Price {

    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }
}
