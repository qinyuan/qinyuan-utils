package com.qinyuan15.utils;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Tool class about random
 * Created by qinyuan on 15-4-4.
 */
public class RandomUtils {
    private RandomUtils() {
    }

    public static <T> List<T> subList(List<? extends T> list, int size) {
        if (size > list.size()) {
            return new ArrayList<>(list);
        }

        List<T> newList = new ArrayList<>();
        for (int i : nextIntegers(0, list.size() - 1, size)) {
            newList.add(list.get(i));
        }
        return newList;
    }

    /**
     * @param lowerBound lower bound of list element
     * @param upperBound upper bound of list element
     * @param size       size of list
     * @return list that contains integers between lowerBound and upperBound
     */
    public static List<Integer> nextIntegers(int lowerBound, int upperBound, int size) {
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }

        Set<Integer> set = new TreeSet<>();

        Random rand = new Random();
        int range = upperBound - lowerBound + 1;

        if (size > range) {
            size = range;
        }

        while (set.size() < size) {
            set.add(lowerBound + rand.nextInt(range));
        }

        return Lists.newArrayList(set);
    }
}
