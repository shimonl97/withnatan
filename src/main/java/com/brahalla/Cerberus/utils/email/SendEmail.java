package com.brahalla.Cerberus.utils.email;

import javax.mail.MessagingException;

/**
 * Created by kfirf on 9/16/16.
 */
public interface SendEmail {
    void sendHtmlEmail(String emailTo, String subject, String content) throws MessagingException;

    void sendRegularEmail(String emailTo, String subject, String content) throws MessagingException;

    void sendWithFile(String emailTo, String subject, String content, String filePath) throws MessagingException;
}
