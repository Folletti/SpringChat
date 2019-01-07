package com.example;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    private static List<User> users = new ArrayList<>();
  //  private static List<Message> messages = new ArrayList<>();
   // private String user;
   // private User currentUser;
   // private StringBuilder stringBuilder = new StringBuilder();

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
    @SendTo("/springchat/greeting")
    public Greeting greeting(UserNameMessage message, String session) throws Exception {
        User currentUser = new User(HtmlUtils.htmlEscape(message.getName()), session);
        users.add(currentUser);
        return new Greeting("Hello, " + currentUser.getFullName() + "!" );
    }
    
    @MessageMapping("/chat")
    @SendTo("/springchat/room")
    public Message messenger(TextMessage message, String session) throws Exception {
        User currentUser = null;
        for(User user : users) {
            if (user.getSessionId().equals(session)) {
                currentUser = user;
            }
            else {
                currentUser = new User("UndefinedUser", "00000");
            }
        }
        return new Message(currentUser, HtmlUtils.htmlEscape(message.getContent()), Instant.now());
    }
    
}
