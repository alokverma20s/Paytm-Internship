package com.scm.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomImageSelector {
    private static final List<String> IMAGE_URLS = Arrays.asList(
        "https://picsum.photos/id/0/200/200",
        "https://picsum.photos/id/1/200/200",
        "https://picsum.photos/id/10/200/200",
        "https://picsum.photos/id/100/200/200",
        "https://picsum.photos/id/1000/200/200",
        "https://picsum.photos/id/1001/200/200",
        "https://picsum.photos/id/1002/200/200",
        "https://picsum.photos/id/1003/200/200",
        "https://picsum.photos/id/1004/200/200",
        "https://picsum.photos/id/1005/200/200",
        "https://picsum.photos/id/1006/200/200",
        "https://picsum.photos/id/1008/200/200",
        "https://picsum.photos/id/1009/200/200",
        "https://picsum.photos/id/101/200/200",
        "https://picsum.photos/id/1010/200/200",
        "https://picsum.photos/id/1011/200/200",
        "https://picsum.photos/id/1012/200/200",
        "https://picsum.photos/id/1013/200/200",
        "https://picsum.photos/id/1014/200/200",
        "https://picsum.photos/id/1015/200/200"
    );

    public static String getRandomImageUrl() {
        Random random = new Random();
        int index = random.nextInt(IMAGE_URLS.size());
        return IMAGE_URLS.get(index);
    }
}