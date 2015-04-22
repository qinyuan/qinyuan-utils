package com.qinyuan15.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Utility class about command
 * Created by qinyuan on 15-4-16.
 */
public class CommandUtils {

    public static Pair<Integer, String> run(final String command) {
        try {
            final Process process = Runtime.getRuntime().exec(command);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));

            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            // 需要注意的是，exitValue要在结果读取结束之后再获取，否则可能会造成阻塞
            int exitValue = process.waitFor();

            return Pair.of(exitValue, sb.toString());
            //return new Pair<>(exitValue, sb.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
