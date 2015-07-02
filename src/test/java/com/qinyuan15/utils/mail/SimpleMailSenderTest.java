package com.qinyuan15.utils.mail;

import org.junit.Test;

/**
 * Test SimpleMailSender
 * Created by qinyuan on 15-7-1.
 */
public class SimpleMailSenderTest {
    @Test
    public void testSend() throws Exception {
        SimpleMailSender sender = new SimpleMailSender("test_300@sina.com", "test12345");
        sender.send("qinyuan15@qq.com", "hello", "world");
    }
}
