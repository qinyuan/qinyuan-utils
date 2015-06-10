package com.qinyuan15.utils.http;

import org.junit.Test;

/**
 * Test ProxyRejectionDao
 * Created by qinyuan on 15-6-4.
 */
public class ProxyRejectionDaoTest {
    @Test
    public void testHasInstance() throws Exception {
        System.out.println(new ProxyRejectionDao().hasInstance("s.etao.com"));
    }
}
