package com.iamkhs.fooddelivery.service.impl;

import com.iamkhs.fooddelivery.entity.User;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailSenderService {

    private final JavaMailSender mailSender;
    private final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    public void sendMail(String to, String mailBody){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            messageHelper.setFrom("noreply@notesventure.app", "noreply@notesventure.app");
            messageHelper.setTo(to);
            messageHelper.setText(mailBody ,true);
            messageHelper.setSubject("Verify Your Account");

            mailSender.send(mimeMessage);
            logger.info("Mail Send Successfully");
        }catch (Exception e){
            logger.error("Email Sending Failed! " + e.getMessage());
        }
    }


    public String createVerificationMailBody(User user, HttpServletRequest request) {
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<p style=\"font-size: 16px;\">Dear ")
                .append(user.getName())
                .append(",</p>")
                .append("<p style=\"font-size: 16px;\">Please click the link below to verify your registration:</p>");

        String verifyUrl = request.getRequestURL().toString() + "/verify?code=" + user.getVerificationCode();

        mailContent.append("<h3 style=\"font-size: 18px;\"><a href=\"")
                .append(verifyUrl)
                .append("\">VERIFY</a></h3>")
                .append("<p style=\"font-size: 14px;\"><strong>This verification link will expire in 5 minute. ")
                .append("If you don't verify your registration within 5 minute, you will need to register again.</strong></p>")
                .append("<p style=\"font-size: 16px;\"><strong>Thank You.</strong><br>The <strong>NotesVenture Team</strong></p>");

        return mailContent.toString();
    }

}
