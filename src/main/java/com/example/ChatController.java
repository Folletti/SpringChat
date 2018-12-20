package com.example;

import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    private List<User> users = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
   // private String user;
    private User currentUser;
    private Session currentSession;
    private StringBuilder stringBuilder = new StringBuilder();

    /*
    @RequestMapping("/")
    public String greeting(@RequestParam(name = "fullName", required = false,
            defaultValue = "user") String name, Model model, HttpSession httpSession) {
        currentUser = null;
        users.forEach(line -> {
            if (line.getSessionId().equals(httpSession.getId())) {
                currentUser = line;
            }
        });
        if (currentUser == null) {
            currentUser = new User(name, httpSession);
            users.add(currentUser);
            model.addAttribute("fullName", currentUser.getFullName());
            return "greeting";
        } else {
            model.addAttribute("fullName", currentUser.getFullName());
            return "chat";
        }
        
    }

    @RequestMapping("/")
    public String chatOpener(@RequestParam(name = "fullName", required = false,
            defaultValue = "user") String name,
                             @RequestParam(name = "message", required = false,
                                     defaultValue = "*Вошел в чат*")
                                     String message, Model model, HttpSession httpSession) {
        if (!name.equals("user")) {
            currentUser.setFullName(name);
            users.forEach(line -> {
                if (line.equals(currentUser)) {
                    line.setFullName(currentUser.getFullName());
                }
            });
        }

        messages.add(new Message(currentUser,  message, Instant.now()));
        stringBuilder.delete(0, stringBuilder.capacity());

        messages.forEach(line -> stringBuilder.append(line.getFormattedMessage())
            .append("<br>"));
        model.addAttribute("messages", stringBuilder.append("<br>"));
        stringBuilder.delete(0, stringBuilder.capacity());

        users.forEach(line -> stringBuilder.append(line.getFullName())
            .append("<br>"));
        model.addAttribute("users", stringBuilder.append("<br>"));

        model.addAttribute("fullName", currentUser.getFullName());

        return "chat";
    }

    @RequestMapping("/refresh")
    public String refresh(Model model) {

        stringBuilder.delete(0, stringBuilder.capacity());
        messages.forEach(line -> stringBuilder.append(line.getFormattedMessage())
            .append("<br>"));
        model.addAttribute("messages", stringBuilder.append("<br>"));

        stringBuilder.delete(0, stringBuilder.capacity());
        users.forEach(line -> stringBuilder.append(line.getFullName()).append("<br>"));
        model.addAttribute("users", stringBuilder.append("<br>"));
        model.addAttribute("fullName", currentUser.getFullName());

        return "chat";
    }
    */
    
    @MessageMapping("/hello")
    @SendTo("/springchat/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
    
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName() + "!"));
    }
    
    
    

}
