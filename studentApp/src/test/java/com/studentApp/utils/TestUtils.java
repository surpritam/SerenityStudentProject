package com.studentApp.utils;

import java.util.Random;

public class TestUtils {

    public static String getRandomValue(){
        Random rn = new Random();
        return Integer.toString(rn.nextInt(10000));
    }
}
