package com.qinyuan15.utils.hibernate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test HibernatePropertyUtils
 * Created by qinyuan on 15-5-29.
 */
public class HibernatePropertyUtilsTest {
    @Test
    public void testGetUsername() {
        assertThat(HibernatePropertyUtils.getUsername()).isEqualTo("root");
    }

    @Test
    public void testGetPassword() {
        assertThat(HibernatePropertyUtils.getPassword()).isEqualTo("qinyuan");
    }

    @Test
    public void testGetUrl() {
        assertThat(HibernatePropertyUtils.getUrl()).isEqualTo("jdbc:mysql://localhost:3306/crawler");
    }

    @Test
    public void testGetHost() {
        assertThat(HibernatePropertyUtils.getHost()).isEqualTo("localhost");
    }

    @Test
    public void testGetDatabase() {
        assertThat(HibernatePropertyUtils.getDatabase()).isEqualTo("crawler");
    }
}
