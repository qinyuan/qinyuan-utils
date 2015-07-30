package com.qinyuan15.utils.mail;

public class MailSenderBuilder {
    public MailSender build(int mailAccountId) {
        MailAccount mailAccount = new MailAccountDao().getInstance(mailAccountId);
        if (mailAccount == null) {
            throw new RuntimeException("No mail account found with mail account id " + mailAccountId);
        }
        return build(mailAccount.getHost(), mailAccount.getUsername(), mailAccount.getPassword());
    }

    public MailSender build(String host, String username, String password) {
        return new SimpleMailSender(host, username, password);
    }
}
