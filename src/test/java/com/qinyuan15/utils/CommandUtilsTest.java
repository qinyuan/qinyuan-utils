package com.qinyuan15.utils;

import com.qinyuan15.utils.CommandUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

/**
 * Test CommandUtils
 * Created by qinyuan on 15-4-16.
 */
public class CommandUtilsTest {
    @Test
    public void testRun() throws Exception {
        Pair<Integer, String> result = CommandUtils.run("which convert");
        System.out.println(result.getKey());
        System.out.println(result.getValue());
        System.out.println(result.getLeft());
        System.out.println(result.getRight());
    }
}
