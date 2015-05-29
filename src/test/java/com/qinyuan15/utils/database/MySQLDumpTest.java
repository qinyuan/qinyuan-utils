package com.qinyuan15.utils.database;

import org.junit.Test;

/**
 * Test MySQLDump
 * Created by qinyuan on 15-5-29.
 */
public class MySQLDumpTest {
    @Test
    public void testRun() throws Exception {
        MySQLDump dump = new MySQLDump();
        dump.setPassword("qinyuan");
        dump.setDatabase("crawler");
        dump.setBackupPath("/tmp/backup2.sql");
        dump.run();
    }
}
