package com.qinyuan15.utils.mvc.controller;

import com.qinyuan15.utils.hibernate.PersistObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectFormItemsBuilderTest {
    @Test
    public void testBuild() throws Exception {
        List<TestBean> testBeans = new ArrayList<>();

        TestBean testBean = new TestBean();
        testBean.setId(1);
        testBean.helloWorld = "value1";
        testBeans.add(testBean);

        testBean = new TestBean();
        testBean.setId(2);
        testBean.helloWorld = "value2";
        testBeans.add(testBean);

        List<SelectFormItemsBuilder.SelectFormItem> items = new SelectFormItemsBuilder().build(testBeans, "helloWorld");
        assertThat(items).hasSize(2);
        assertThat(items.get(0).getId()).isEqualTo(1);
        assertThat(items.get(0).getValue()).isEqualTo("value1");
        assertThat(items.get(1).getId()).isEqualTo(2);
        assertThat(items.get(1).getValue()).isEqualTo("value2");
    }

    public static class TestBean extends PersistObject {
        private String helloWorld;

        public String getHelloWorld() {
            return helloWorld;
        }
    }
}
