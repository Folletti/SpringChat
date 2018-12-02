package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class ChatController {
    private static ArrayList <String> users = new ArrayList<>();
    private static ArrayList <String> messages = new ArrayList<>();
    private String user;
    Model model;
    String fullName;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name = "fullName", required = false, defaultValue = "user") String name, Model model) {
        model.addAttribute("fullName", name);
        this.model = model;
        this.fullName = name;
        return "greeting";
    }


    @RequestMapping("/chat")
    public String chatOpener(@RequestParam(name = "fullName", required = false, defaultValue = "user") String name, @RequestParam(name = "message", required = false, defaultValue = "----") String message, Model model) {
        if (fullName.equals("user")) fullName = name;
        model.addAttribute("fullName", fullName);
        model.addAttribute("message", message);
        users.add(name);
        messages.add(message);
        this.model = model;
        return "chat";
    }

}
