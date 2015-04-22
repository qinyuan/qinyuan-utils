package com.qinyuan15.utils;

import com.google.common.collect.Lists;
import com.qinyuan15.utils.RandomUtils;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test RandomUtils
 * Created by qinyuan on 15-4-4.
 */
public class RandomUtilsTest {

    @Test
    public void testSubList() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.subList(list, 3));
        }
    }

    @Test
    public void testNextIntegers() throws Exception {
        List<Integer> integers = RandomUtils.nextIntegers(1, 3, 3);
        assertThat(integers).containsExactly(1, 2, 3);

        integers = RandomUtils.nextIntegers(1, 3, 4);
        assertThat(integers).containsExactly(1, 2, 3);

        for (int i = 0; i < 5; i++) {
            integers = RandomUtils.nextIntegers(1, 5, 3);
            System.out.println(integers);
        }
    }
}
