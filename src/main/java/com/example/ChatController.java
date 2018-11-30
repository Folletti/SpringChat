package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class ChatController {


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name = "fullName", required = false, defaultValue = "user") String name, Model model) {
        model.addAttribute("fullName", name);
        return "greeting";
    }

    @RequestMapping("/chat")
    public String chatOpener(@RequestParam(name = "fullName", required = false, defaultValue = "user") String name, Model model) {
        model.addAttribute("fullName", name);
        return "chat";
    }

}
