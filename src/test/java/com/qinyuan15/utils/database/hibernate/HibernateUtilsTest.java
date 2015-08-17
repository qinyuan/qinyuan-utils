package com.qinyuan15.utils.database.hibernate;

import com.qinyuan15.utils.security.User;
import org.junit.Test;

/**
 * Test HibernateUtil
 * Created by qinyuan on 14-12-31.
 */
public class HibernateUtilsTest {

    @Test
    public void testGetCount() {
        //long count = HibernateUtils.getCount("Commodity");
        //assertThat(HibernateUtils.getCount("Commodity", "id>0")).isEqualTo(count);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("张三");
        user.setPassword("test");
        HibernateUtils.save(user);
    }
}
