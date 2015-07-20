package com.qinyuan15.utils.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to validate mail address
 * Created by qinyuan on 15-6-29.
 */
public class MailAddressValidator {
    private final static Logger LOGGER = LoggerFactory.getLogger(MailAddressValidator.class);

    public boolean validate(String mailAddress) {
        try {
            String check = "^[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(mailAddress);
            return matcher.matches();
        } catch (Exception e) {
            LOGGER.error("fail to validate mail '{}', info: {}", mailAddress, e);
            return false;
        }
    }
}
