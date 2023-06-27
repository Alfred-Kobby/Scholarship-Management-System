package com.gimpa.project.scholarship.controller;

import com.gimpa.project.scholarship.model.GeneralResponse;
import com.gimpa.project.scholarship.model.LoginRequest;
import com.gimpa.project.scholarship.model.ServiceResponse;
import com.gimpa.project.scholarship.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/")
    public String home(){
        return "login";
    }
}
