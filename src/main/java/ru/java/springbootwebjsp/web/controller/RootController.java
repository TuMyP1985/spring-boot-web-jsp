package ru.java.springbootwebjsp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.java.springbootwebjsp.service.UserService;

import java.util.Map;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String root(Map<String, Object> model) {
        return "index";
    }

    @GetMapping("/usersajax")
    public String getUsers(Model model) {
        log.info("users");
        model.addAttribute("users", userService.getAll());
        return "usersajax";
    }

    @GetMapping("/examination")
    public String getExamination(Model model) {
        log.info("examinations");
        return "/examination";
    }
}

