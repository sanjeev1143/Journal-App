package com.daker.journalApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {


    @RequestMapping(value = "/health-check", method = RequestMethod.GET)
    public String healthCheck(){
        return "OK";
    }

}
