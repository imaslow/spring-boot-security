package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public String showUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute(new User());
        List<Role> roles = roleService.getRolesList();
        model.addAttribute("allRoles", roles);
        return "create";
    }
    @PostMapping("/adduser")
    public String createUser(@Validated(User.class) @ModelAttribute("users") User user,
                             @RequestParam("role") List<String> values,
                             BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        Set<Role> roleSet = userService.getSetOfRoles(values);
        userService.createUser(user);
        user.setRoles(roleSet);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable Long id, Model model) {
        User userEdit = userService.readUser(id);
        List<Role> roles = roleService.getRolesList();
        model.addAttribute("allRoles", roles);
        model.addAttribute("user", userEdit);
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@Validated(User.class) @ModelAttribute("users") User user,
    @RequestParam("role") List<String> values,
    BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        Set<Role> roleSet = userService.getSetOfRoles(values);
        userService.updateUser(user);
        user.setRoles(roleSet);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {
        User user = userService.readUser(id);
        if(user != null) {
            userService.deleteUser(id);
            model.addAttribute("messege", "user " + user.getUsername() + " succesfully deleted");
        } else {
            model.addAttribute("messege", "no such user");
        }

        return "redirect:/admin";
    }
}
