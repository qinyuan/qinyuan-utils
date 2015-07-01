package com.qinyuan15.utils.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Class to build Authentication of mail
 * Created by qinyuan on 15-7-1.
 */
class MailAuthenticator extends Authenticator {
    private final String username;
    private final String password;

    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
