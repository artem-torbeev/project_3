package com.example.application.controller;

import com.example.application.model.FormUser;
import com.example.application.model.Role;
import com.example.application.model.User;
import com.example.application.repository.RoleRepository;
import com.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAllUser(ModelMap model) {
        List<User> users = userService.findAll();
        model.addAttribute("listUser", users);
        model.addAttribute("form", new FormUser());
        return "admin";
    }

    @PostMapping({"admin/create", "/admin/edit"})
    public String createUser(@ModelAttribute("form") FormUser formUser) {
        userService.saveUser(formUser);
        return "redirect:/admin";
    }


}
