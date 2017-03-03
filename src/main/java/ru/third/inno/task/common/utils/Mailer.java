package ru.third.inno.task.common.utils;

/*import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/

import java.io.IOException;

/**
 * Created by yy on 27.02.17.
 */
public class Mailer {
    public static void sendMail(String text, String ea) throws IOException {

     /*   String user;
        String pass;


        final String username = "grigoriorloff@gmail.com";            final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ea));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(ea));
            message.setSubject("Some message from IS");
            message.setText(text);

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }*/
    }
}
