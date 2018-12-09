package com.example;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Scope ("session")
@Controller
public class ChatController {
    private static ArrayList <String> users = new ArrayList<>();
    private static ArrayList <String> messages = new ArrayList<>();
    private String user;
    private String fullName;
    private StringBuilder stringBuilder = new StringBuilder();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name = "fullName", required = false, defaultValue = "user") String name, Model model) {
        model.addAttribute("fullName", name);
        //this.model = model;
        fullName = name;
        users.add(name);
        //@Req
        //Session session = new Session();
        //session.
        return "greeting";
    }

    @RequestMapping("/chat")
    public String chatOpener(@RequestParam(name = "fullName", required = false, defaultValue = "user") String name,
                             @RequestParam(name = "message", required = false, defaultValue = "*Вошел в чат*") String message, Model model) {
        if (!name.equals("user"))
        {
            fullName = name;
            users.remove("user");
            users.add(name);
        }

        messages.add(Instant.now().atZone(ZoneId.of("Europe/Samara")).format(formatter).toString() + " | " + fullName + ": " + message);
        stringBuilder.delete(0, stringBuilder.capacity());
        messages.forEach((line) -> stringBuilder.append(line + "<br>"));
        model.addAttribute("messages", stringBuilder.toString() + "<br>");

        stringBuilder.delete(0, stringBuilder.capacity());
        users.forEach((line) -> stringBuilder.append(line + "<br>"));
        model.addAttribute("users", stringBuilder.toString() + "<br>");

        model.addAttribute("fullName", fullName);
        //model.addAttribute("message", message);


        //this.model = model;
        return "chat";
    }

    @RequestMapping("/refresh")
    public String refresh(Model model) {

        stringBuilder.delete(0, stringBuilder.capacity());
        messages.forEach((line) -> stringBuilder.append(line + "<br>"));
        model.addAttribute("messages", stringBuilder.toString() + "<br>");
        stringBuilder.delete(0, stringBuilder.capacity());
        users.forEach((line) -> stringBuilder.append(line + "<br>"));
        model.addAttribute("users", stringBuilder.toString() + "<br>");
        model.addAttribute("fullName", fullName);

        return "chat";
    }

}
