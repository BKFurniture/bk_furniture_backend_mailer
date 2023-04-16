package com.bkfunirture.mailer.service;

import com.bkfunirture.mailer.request.DesignRequest;
import com.bkfunirture.mailer.request.PayRequest;
import com.bkfunirture.mailer.request.SaleRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
    @Value("${urlSignIn}")
    private String urlSignIn;
    @Value("${headerImgUrl}")
    private String headerImgUrl;
    @Value("${footerImgUrl}")
    private String footerImgUrl;

    public void signUpSuccess(String gmail, String userFullName) {
        try {
            Template template = config.getTemplate("sign-up.ftl");
            Map<String, Object> model = new HashMap<>();
            model.put("userFullName", userFullName);
            model.put("urlSignIn", urlSignIn);
            model.put("headerImgUrl", headerImgUrl);
            model.put("footerImgUrl", footerImgUrl);
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
    public void design(String gmail, String userFullName, DesignRequest designRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM HH:mm");
            model.put("userFullName", userFullName);
            model.put("numDay", 5);
            model.put("designRequest", designRequest);
            model.put("headerImgUrl", headerImgUrl);
            model.put("footerImgUrl", footerImgUrl);
            context.setVariables(model);
            helper.setFrom(sender);
            helper.setTo(gmail);
            helper.setSubject("Receipt of acknowledgment for received design");
            String html = springTemplateEngine.process("design.html", context);
            helper.setText(html, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            return;
        }
    }
    public void sale(SaleRequest saleRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM HH:mm");
            model.put("saleRequest",saleRequest);
            model.put("headerImgUrl", headerImgUrl);
            model.put("footerImgUrl", footerImgUrl);
            context.setVariables(model);
            helper.setFrom(sender);
            helper.setTo(saleRequest.getUserMails());
            helper.setSubject("Special discount code: "+saleRequest.getDiscountPercentage()+"% off!");
            String html = springTemplateEngine.process("sale.html", context);
            helper.setText(html, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            return;
        }
    }
    public void checkout(String gmail, String userFullName, PayRequest payRequest){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("userFullName", userFullName);
            model.put("payRequest",payRequest);
            model.put("headerImgUrl", headerImgUrl);
            model.put("footerImgUrl", footerImgUrl);
            context.setVariables(model);
            helper.setFrom(sender);
            helper.setTo(gmail);
            helper.setSubject("Order Confirmation: Payment Successfully Processed for Your Purchase");
            String html = springTemplateEngine.process("checkout.html", context);
            helper.setText(html, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            return;
        }
    }
}