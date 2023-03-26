package com.bkfunirture.mailer.controller;

import com.bkfunirture.mailer.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "mailer/{gmail}/{userFullName}")
public class MailerController {
    @Autowired
    MailerService mailService;

    @GetMapping("sign-up")
    public String signUpSuccess(@PathVariable String gmail,@PathVariable String userFullName){
        return mailService.signUpSuccess(gmail,userFullName);
    }
    @GetMapping("design/{numDay}")
    public String design(@PathVariable String gmail,@PathVariable String userFullName,@PathVariable int numDay){
        return mailService.design(gmail,userFullName,numDay);
    }

//    @GetMapping("sale")
}