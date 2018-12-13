package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.postgresql.Driver;

@Scope ("session")
@Controller
public class ChatController {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Message> messages = new ArrayList<>();
    private String user;
    private User currentUser;
    private StringBuilder stringBuilder = new StringBuilder();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name = "fullName", required = false,
            defaultValue = "user") String name, Model model) {

        currentUser = new User(name);
        users.add(currentUser);

        model.addAttribute("fullName", currentUser.getFullName());
        return "greeting";
    }

    @RequestMapping("/chat")
    public String chatOpener(@RequestParam(name = "fullName", required = false,
            defaultValue = "user") String name,
                             @RequestParam(name = "message", required = false,
                                     defaultValue = "*Вошел в чат*")
                                     String message, Model model) {
        if (!name.equals("user")) {
            currentUser.setFullName(name);
            users.forEach( (line) -> {
                if (line.equals(currentUser)) {
                    line.setFullName(currentUser.getFullName());
                }
            });
        }

        jdbcTemplate.execute("drop table chatdb if exists");
        jdbcTemplate.execute("create table chatdb " +
                "(id int," +
                "fullName varchar(100)");
        jdbcTemplate.batchUpdate("insert into chatdb values (1, Stupko M.)");

        messages.add(new Message(currentUser,  message, Instant.now()));
        stringBuilder.delete(0, stringBuilder.capacity());

        messages.forEach((line) -> stringBuilder.append(line
                .getFormattedMessage() + "<br>"));
        model.addAttribute("messages", stringBuilder.toString() + "<br>");
        stringBuilder.delete(0, stringBuilder.capacity());

        users.forEach((line) -> stringBuilder.append(line.getFullName()
                + "<br>"));
        model.addAttribute("users", stringBuilder.toString() + "<br>");

        model.addAttribute("fullName", currentUser.getFullName());

        return "chat";
    }

    @RequestMapping("/refresh")
    public String refresh(Model model) {

        stringBuilder.delete(0, stringBuilder.capacity());
        messages.forEach((line) -> stringBuilder.append(line
                .getFormattedMessage() + "<br>"));
        model.addAttribute("messages", stringBuilder.toString() + "<br>");

        stringBuilder.delete(0, stringBuilder.capacity());
        users.forEach((line) -> stringBuilder.append(line.getFullName()
                + "<br>"));
        model.addAttribute("users", stringBuilder.toString() + "<br>");
        model.addAttribute("fullName", currentUser.getFullName());

        return "chat";
    }

}
