package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.ArrayList;


@Controller
public class ChatController {
    private static ArrayList <String> users = new ArrayList<>();
    private static ArrayList <String> messages = new ArrayList<>();
    private String user;
    //Model model;
    private String fullName;
    private StringBuilder stringBuilder = new StringBuilder();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name = "fullName", required = false, defaultValue = "user") String name, Model model) {
        model.addAttribute("fullName", name);
        //this.model = model;
        fullName = name;
        users.add(name);
        return "greeting";
    }

    @RequestMapping("/chat")
    public String chatOpener(@RequestParam(name = "fullName", required = false, defaultValue = "user") String name, @RequestParam(name = "message", required = false, defaultValue = "----") String message, Model model) {
        if (fullName.equals("user"))
        {
            fullName = name;
            users.remove("user");
            users.add(name);
        }

        messages.add(Instant.now().atZone(ZoneId.of("Europe/Samara")).toString() + " | " + fullName + ": " + message);
        stringBuilder.delete(0, stringBuilder.capacity());
        messages.forEach((line) -> stringBuilder.append(line + "<br>"));

        model.addAttribute("fullName", fullName);
        model.addAttribute("message", message);
        model.addAttribute("messages", stringBuilder.toString() + "<br>");


        //this.model = model;
        return "chat";
    }

}
