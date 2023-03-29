package com.bkfunirture.mailer.service;

import com.bkfunirture.mailer.model.SaleProduct;
import com.bkfunirture.mailer.request.SaleRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MailerService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine springTemplateEngine;
    @Autowired
    private Configuration config;
    @Value("${spring.mail.username}")
    private String sender;
    @Value("${url}")
    private String url;
    @Value("${urlSale}")
    private String urlSale;

    public void signUpSuccess(String gmail, String userFullName) {
        try {
            Template template = config.getTemplate("sign-up.ftl");
            Map<String, Object> model = new HashMap<>();
            model.put("userFullName", userFullName);
            model.put("url", url);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(gmail);
            helper.setSubject("Welcome to BKFurniture");
            helper.setFrom(sender);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            return;
        }
    }

    public void design(String gmail, String userFullName, int numDay) {
        try {
            Template template = config.getTemplate("design.ftl");
            Map<String, Object> model = new HashMap<>();
            model.put("userFullName", userFullName);
            model.put("numDay", numDay);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(htmlMsg, true);
            helper.setTo(gmail);
            helper.setSubject("Receipt of acknowledgment for received design");
            helper.setFrom(sender);
            javaMailSender.send(mimeMessage);
//            return "Design success";
        } catch (Exception e) {
            return;
        }
    }

    public void sale(String gmail, String userFullName, SaleRequest saleRequest) {
        try {
//            List<SaleProduct> saleProducts=new ArrayList<>();
//            SaleProduct saleProduct=new SaleProduct("n1","url1",null,0,0,0);
//            SaleProduct saleProduct1=new SaleProduct("n2","url2",null,0,0,0);
//            saleProducts.add(saleProduct);
//            saleProducts.add(saleProduct1);
//            saleRequest=new SaleRequest("t","https://i.imgur.com/VGtipqs.png","desc", saleProducts);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM HH:mm");
            model.put("userFullName", userFullName);
            model.put("saleRequest",saleRequest);
            model.put("dateFormat",dateFormat);
            model.put("urlSale",urlSale);
//            String date= dateFormat.format(saleRequest.getSaleProduct().get(0).getEndDate());
            saleRequest.getSaleProduct().forEach((s)->{
                s.setDateString(dateFormat.format(s.getEndDate()));
            });
            System.out.println(saleRequest.getSaleProduct().get(0).getEndDate());
            context.setVariables(model);
            helper.setFrom(sender);
            helper.setTo(gmail);
            helper.setSubject("Sale");
            String html = springTemplateEngine.process("design.html", context);
            helper.setText(html, true);
            javaMailSender.send(message);
//            return "Design success";
        } catch (Exception e) {
            return;
        }
    }
}