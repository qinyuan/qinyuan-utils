package com.qinyuan15.utils.tel;

import org.springframework.util.StringUtils;

/**
 * Class to validate telephone number
 * Created by qinyuan on 15-7-31.
 */
public class TelValidator {
    public boolean validate(String tel) {
        return StringUtils.hasText(tel) && tel.matches("^\\d{11}$");
    }
}
