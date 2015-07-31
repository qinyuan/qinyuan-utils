package com.qinyuan15.utils.tel;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TelValidatorTest {
    @Test
    public void testValidate() throws Exception {
        TelValidator validator = new TelValidator();
        assertThat(validator.validate("15051112213")).isTrue();
        assertThat(validator.validate("1505111221")).isFalse();
    }
}
