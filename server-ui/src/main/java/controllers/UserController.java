package controllers;

import dao.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import services.UserService;

@Controller
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/list")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @PostMapping("/users/add")
    void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.listUsers());
        return "redirect:/users/list";
    }
}

