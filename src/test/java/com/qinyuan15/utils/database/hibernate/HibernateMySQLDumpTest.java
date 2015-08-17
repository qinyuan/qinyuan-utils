package com.qinyuan15.utils.database.hibernate;

import org.junit.Test;

/**
 * Test HibernateMySQLDump
 * Created by qinyuan on 15-5-29.
 */
public class HibernateMySQLDumpTest {
    @Test
    public void testRun() throws Exception {
        HibernateMySQLDump dump = new HibernateMySQLDump("/tmp/");
        dump.run();
    }
}
