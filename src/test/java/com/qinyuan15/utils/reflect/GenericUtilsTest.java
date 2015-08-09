package com.qinyuan15.utils.reflect;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GenericUtilsTest {
    @Test
    public void testGetRealTypeOfGenericArgument() {
        assertThat(GenericUtils.getRealTypeOfGenericArgument(TestClass.class)).isEqualTo(StringBuffer.class);
        assertThat(GenericUtils.getRealTypeOfGenericArgument(TestClass2.class)).isEqualTo(StringBuffer.class);
        assertThat(GenericUtils.getRealTypeOfGenericArgument(TestClass3.class)).isEqualTo(StringBuffer.class);
    }


    public static class TestClass extends TestAbstractClass<StringBuffer> {
    }

    public static class TestClass2 implements TestInterface<StringBuffer> {
    }

    private static class TestClass3 extends TestAbstractClass<StringBuffer> {
    }

    public static interface TestInterface<T> {
    }

    public abstract static class TestAbstractClass<T> {
    }
}
