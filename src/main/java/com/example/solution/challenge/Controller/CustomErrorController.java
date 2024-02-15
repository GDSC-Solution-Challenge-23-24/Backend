package com.example.solution.challenge.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Provide your custom error page path or logic here
        return "error";
    }


    public String getErrorPath() {
        return "/error";
    }
}

