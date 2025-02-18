package com.scm.helpers;

import java.util.Random;

public class RandomImageSelector {
    public static String getRandomImageUrl() {
        Random random = new Random();
        int index = random.nextInt(1024);
        return "https://picsum.photos/id/"+index+"/200/200";
    }
}