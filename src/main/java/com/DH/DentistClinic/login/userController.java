package com.DH.DentistClinic.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {
    @GetMapping("/")
    public String home(){
        return "<h1>Wellcome</h1>";
    }

    @GetMapping("/user")
    public String user(){
        return "<h1>Wellcome User</h1>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1>Wellcome Admin</h1>";
    }

}
