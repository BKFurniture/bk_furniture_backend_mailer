package com.bkfunirture.mailer.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;


@Service
public class MailerService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration config;
    @Value("${spring.mail.username}") private String sender;
    @Value("${url}") private String url;
    public String signUpSuccess(String gmail,String userFullName) {
        try {
            Template template = config.getTemplate("sign-up.ftl");
            Map<String, Object> model = new HashMap<>();
            model.put("userFullName",userFullName);
            model.put("url",url);
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
            String htmlMsg= FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(gmail);
            helper.setSubject("Welcome to BKFurniture");
            helper.setFrom(sender);
            javaMailSender.send(mimeMessage);
            return "Mail signup success";
        }
        catch (Exception e){
            return "Error mail signup";
        }
    }

    public String design(String gmail,String userFullName,int numDay) {
        try {
            Template template = config.getTemplate("design.ftl");
            Map<String, Object> model = new HashMap<>();
            model.put("userFullName",userFullName);
            model.put("numDay",numDay);
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
            String htmlMsg= FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(gmail);
            helper.setSubject("Welcome to BKFurniture");
            helper.setFrom(sender);
            javaMailSender.send(mimeMessage);
            return "Design success";
        }
        catch (Exception e){
            return "Error mail design";
        }
    }
}