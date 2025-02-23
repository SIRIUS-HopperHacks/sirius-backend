package com.app.sirius.config;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class GmailSMTP {
    public static void main(String[] args) {
        final String username = "sirius20250223@gmail.com"; // 본인 Gmail
        final String password = "rpfp wbta wcrh ocnr";   // 앱 비밀번호 필요

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sirius20250223@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("wnsghd0902@gmail.com")
            );
            message.setSubject("Test Mail");
            message.setText("Hello, this is a test email from Java!");

            Transport.send(message);
            System.out.println("Email Sent Successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
