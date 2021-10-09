package controllers;

import dao.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        //     model.addAttribute("userInfo", new UserInfo());
        return "users";
    }

    @GetMapping("users/{id}")
    public String getUser(Model model, @PathVariable("id") Integer id) {
        User userInfo = userService.getUser(id);
        model.addAttribute("userInfo", userInfo);
        return "edit";
    }

    @PostMapping("/users/add")
    void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@RequestBody Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.listUsers());
        return "users";
    }
}

