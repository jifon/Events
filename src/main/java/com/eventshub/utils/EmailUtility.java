package com.eventshub.utils;

import com.eventshub.model.User;
import lombok.experimental.UtilityClass;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@UtilityClass
public class EmailUtility {

    //    final String siteURL =
//            ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    private final String siteURL = "http://localhost:8080/";
    public void sendVerificationEmail(User user, JavaMailSender mailSender)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "renitt3test@gmail.com";
        String senderName = "EventsHub";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Eve.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName());
        String verifyURL = siteURL + "api/auth/verifyUser?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);
        mailSender.send(message);

        System.out.println(content);

    }

    public void sendTicketToEmail(User user, String eventName, String codeTicket, JavaMailSender mailSender)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "renitt3test@gmail.com";
        String senderName = "E-Ticket";
        String subject = "You have successfully registered";
        String content = "Dear [[name]],<br>"
                + "You have successfully registered for the event: [[event]]<br>"
                + "Your unique electronic code for this event: [[code]] <br>"
                + "Thank you,<br>"
                + "Eve.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName());
        content = content.replace("[[event]]", eventName);
        content = content.replace("[[event]]", codeTicket);


        String verifyURL = siteURL + "api/auth/verifyUser?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);
        mailSender.send(message);

        System.out.println(content);

    }
}