package com.qinyuan15.utils.hibernate;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test HibernateListBuilder
 * Created by qinyuan on 15-6-26.
 */
public class HibernateListBuilderTest {
    @Test
    public void testBuildBySQL() throws Exception {
        List<Object[]> list = new HibernateListBuilder().buildBySQL("SELECT * FROM proxy");
        System.out.println(list.size());
        for (Object[] objects : list) {
            assertThat(objects).hasSize(6);
        }
    }

    @Test
    public void testCountBySQL() {
        HibernateListBuilder listBuilder = new HibernateListBuilder();
        assertThat(listBuilder.countBySQL("proxy")).isEqualTo(listBuilder.count(Proxy.class))
                .isEqualTo(listBuilder.count("Proxy"));
        System.out.println(listBuilder.count("Proxy"));
    }
}
