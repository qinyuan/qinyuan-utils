package com.qinyuan15.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Utility class about command
 * Created by qinyuan on 15-4-16.
 */
public class CommandUtils {

    /**
     * Execute external command
     *
     * @param command external command
     * @return a pair whose key is exit value and value is execute result string
     */
    public static Pair<Integer, String> run(final String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            // we must fetch exitValue after result read, or program may block
            int exitValue = process.waitFor();

            return Pair.of(exitValue, sb.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
