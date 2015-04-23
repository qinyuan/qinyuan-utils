package com.qinyuan15.utils;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test RandomUtils
 * Created by qinyuan on 15-4-4.
 */
public class RandomUtilsTest {
    @Test
    public void testSubList() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            List<Integer> subList = RandomUtils.subList(list, 3);
            assertThat(subList).hasSize(3);
            set.addAll(subList);
        }
        assertThat(set).hasSameSizeAs(list);
    }

    @Test
    public void testNextIntegers() throws Exception {
        List<Integer> integers = RandomUtils.nextIntegers(1, 3, 3);
        assertThat(integers).containsExactly(1, 2, 3);

        integers = RandomUtils.nextIntegers(1, 3, 4);
        assertThat(integers).containsExactly(1, 2, 3);

        Set<Integer> integerSet = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            integers = RandomUtils.nextIntegers(1, 5, 3);
            assertThat(integers).hasSize(3);
            integerSet.addAll(integers);
        }
        assertThat(integerSet).hasSize(5);
    }
}
