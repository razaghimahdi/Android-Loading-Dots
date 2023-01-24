package com.razzaghimahdi78.dotsloading.core;

public class ConvertorImpl implements Convertor {
    @Override
    public int convertDotSize(int value) {
        int size;
        switch (value) {
            case 0:
                size = 10;
                break;
            case 1:
                size = 15;
                break;
            case 3:
                size = 30;
                break;
            case 4:
                size = 40;
                break;

            default:
                size = 20;
                break;
        }
        return size;
    }

    @Override
    public int convertDotSize(DotSize value) {
        int size;
        switch (value) {
            case TINY:
                size = 10;
                break;
            case SMALL:
                size = 15;
                break;
            case BIG:
                size = 30;
                break;
            case HUGE:
                size = 40;
                break;
            default:
                size = 20;
                break;
        }
        return size;
    }
}
