package com.example.photographerjava.services;

import com.example.photographerjava.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.example.thymeleafsptingbootexample.model.Mail;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;



@Service
public class MailService {

    @Autowired
    private  JavaMailSender emailSender;

    @Autowired
    private  SpringTemplateEngine templateEngine;

    public void sendEmail(User user){
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("user", user);
        properties.put("sign", "Dana Brenych");


        Mail mail = Mail. builder()
                .from("danabrenych55566@gmail.com")
                .to(user.getEmail())
                .htmlTemplate(new Mail.HtmlTemplate("User", properties))
                .subject("This is page with confirmation your account")
                .build();



        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            String html = getHtmlContent(mail);

            helper.setTo(mail.getTo());
            helper.setFrom(mail.getFrom());
            helper.setSubject(mail.getSubject());
            helper.setText(html, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


        emailSender.send(message);
    }

    private String getHtmlContent(Mail mail) {
        Context context = new Context();
        context.setVariables(mail.getHtmlTemplate().getProps());
        return templateEngine.process(mail.getHtmlTemplate().getTemplate(), context);
    }
}
