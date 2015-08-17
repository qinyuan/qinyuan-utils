package com.qinyuan15.utils.database.hibernate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test SQLConditionBuilder
 * Created by qinyuan on 15-5-26.
 */
public class SQLConditionBuilderTest {
    @Test
    public void testBuild() throws Exception {
        SQLConditionBuilder builder = new SQLConditionBuilder();

        builder.addFilter("hello=:hello");
        assertThat(builder.build()).isEqualTo(" WHERE (hello=:hello)");

        builder.addFilter("world=:world");
        assertThat(builder.build()).isEqualTo(" WHERE (hello=:hello) AND (world=:world)");

        builder.addGroup("field1");
        assertThat(builder.build()).isEqualTo(" WHERE (hello=:hello) AND (world=:world) GROUP BY field1");

        builder.addGroup("field2");
        assertThat(builder.build()).isEqualTo(" WHERE (hello=:hello) AND (world=:world) GROUP BY field1,field2");

        builder.addOrder("field3", true);
        assertThat(builder.build()).isEqualTo(
                " WHERE (hello=:hello) AND (world=:world) GROUP BY field1,field2 ORDER BY field3 ASC");

        builder.addOrder("field4", false);
        assertThat(builder.build()).isEqualTo(
                " WHERE (hello=:hello) AND (world=:world) GROUP BY field1,field2 ORDER BY field3 ASC,field4 DESC");

        builder = new SQLConditionBuilder().addGroup("field1").addGroup("field2");
        assertThat(builder.build()).isEqualTo(" GROUP BY field1,field2");

        builder.addOrder("field3", false);
        assertThat(builder.build()).isEqualTo(" GROUP BY field1,field2 ORDER BY field3 DESC");

        builder.addOrder("field4", true);
        assertThat(builder.build()).isEqualTo(" GROUP BY field1,field2 ORDER BY field3 DESC,field4 ASC");

        builder = new SQLConditionBuilder().addEqualFilter("testField");
        assertThat(builder.build()).isEqualTo(" WHERE (testField=:testField)");
    }
}
