package com.qinyuan15.utils.mail;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test MailAddressValidator
 * Created by qinyuan on 15-6-29.
 */
public class MailAddressValidatorTest {
    @Test
    public void testValidate() throws Exception {
        MailAddressValidator validator = new MailAddressValidator();
        assertThat(validator.validate("2q@qq.com")).isTrue();
        assertThat(validator.validate("2&q@qq.com")).isFalse();
        assertThat(validator.validate("HelloWorld")).isFalse();
    }
}
