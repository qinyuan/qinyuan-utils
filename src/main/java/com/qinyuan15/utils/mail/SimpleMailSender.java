package com.qinyuan15.utils.mail;

import com.google.common.collect.Lists;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.List;
import java.util.Properties;

/**
 * Class to send mail
 * Created by qinyuan on 15-7-1.
 */
public class SimpleMailSender {

    private MailAuthenticator authenticator;
    private Session session;

    public SimpleMailSender(String smtpHostname, String username, String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", smtpHostname);
        authenticator = new MailAuthenticator(username, password);
        session = Session.getInstance(props, authenticator);
    }

    public SimpleMailSender(String username, String password) {
        this(parseSMTPFromUsername(username), username, password);
    }

    private static String parseSMTPFromUsername(String username) {
        return "smtp." + username.split("@")[1];
    }

    public void send(String recipient, String subject, Object content) {
        send(Lists.newArrayList(recipient), subject, content);
    }

    public void send(List<String> recipients, String subject, Object content) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(authenticator.getUsername()));

            InternetAddress[] addresses = new InternetAddress[recipients.size()];
            for (int i = 0; i < addresses.length; i++) {
                addresses[i] = new InternetAddress(recipients.get(i));
            }
            message.setRecipients(RecipientType.TO, addresses);

            message.setSubject(subject);
            message.setContent(content.toString(), "text/html;charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
