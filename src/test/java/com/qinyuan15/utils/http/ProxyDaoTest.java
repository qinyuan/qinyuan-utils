package com.qinyuan15.utils.http;

import org.junit.Test;

import java.util.List;

/**
 * Test ProxyDao
 * Created by qinyuan on 15-5-16.
 */
public class ProxyDaoTest {
    private ProxyDao dao = new ProxyDao();

    @Test
    public void testGetInstances() {
        List<Proxy> proxies = dao.getInstances(10);
        for (Proxy proxy : proxies) {
            System.out.println(proxy.getId() + " " + proxy.getSpeed());
        }
    }

    @Test
    public void testGetSlowInstances() {
        List<Proxy> proxies = dao.getSlowInstances(20);
        for (Proxy proxy : proxies) {
            System.out.println(proxy.getId() + " " + proxy.getSpeed());
        }
    }

    @Test
    public void getCount() {
        System.out.println(dao.getCount());
    }

    @Test
    public void testSlowCount() {
        System.out.println(dao.getSlowCount());
    }
}
