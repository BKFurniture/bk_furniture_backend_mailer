package com.bkfunirture.mailer.controller;

import com.bkfunirture.mailer.request.DesignRequest;
import com.bkfunirture.mailer.request.PayRequest;
import com.bkfunirture.mailer.request.SaleRequest;
import com.bkfunirture.mailer.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000/","https://bk-furniture-frontend.vercel.app/"}, maxAge = 3600)
@RestController
@RequestMapping(path = "mailer/{gmail}/{userFullName}")
public class MailerController {
    @Autowired
    MailerService mailService;

    @GetMapping("sign-up")
    public ResponseEntity<String> signUpSuccess(@PathVariable String gmail, @PathVariable String userFullName){
        mailService.signUpSuccess(gmail,userFullName);
        return new ResponseEntity<>("Mail signup success", HttpStatus.OK) ;
    }
    @PostMapping("design")
    public ResponseEntity<String> design(@PathVariable String gmail, @PathVariable String userFullName, @RequestBody DesignRequest designRequest){
        mailService.design(gmail,userFullName,designRequest);
        return new ResponseEntity<>("Mail design success", HttpStatus.OK) ;
    }
    @PostMapping("sale")
    public ResponseEntity<String> sale(@PathVariable String gmail, @PathVariable String userFullName, @RequestBody SaleRequest saleRequest){
        mailService.sale(gmail,userFullName,saleRequest);
        return new ResponseEntity<>("Mail sale success", HttpStatus.OK) ;
    }
    @PostMapping("checkout")
    public ResponseEntity<String> checkout(@PathVariable String gmail, @PathVariable String userFullName, @RequestBody PayRequest payRequest){
        mailService.checkout(gmail,userFullName,payRequest);
        return new ResponseEntity<>("Mail checkout success", HttpStatus.OK) ;
    }
}