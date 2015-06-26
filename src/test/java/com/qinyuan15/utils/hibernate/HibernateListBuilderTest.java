package com.qinyuan15.utils.hibernate;

import org.junit.Test;

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
}
