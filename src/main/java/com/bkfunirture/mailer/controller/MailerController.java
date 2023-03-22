package com.bkfunirture.mailer.controller;

import com.bkfunirture.mailer.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "mailer")
public class MailerController {
    @Autowired
    MailerService mailService;

    @GetMapping("sign-up/{gmail}/{userFullName}")
    public String signUpSuccess(@PathVariable String gmail,@PathVariable String userFullName){
        return mailService.signUpSuccess(gmail,userFullName);
    }
}