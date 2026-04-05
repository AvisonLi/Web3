package hkmu.gp.online_course.controller;

import hkmu.gp.online_course.dto.UserDto;
import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto dto, @RequestParam String role) {
        userService.register(dto, "ROLE_" + role.toUpperCase());
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@AuthenticationPrincipal UserDetails currentUser,
                                @RequestParam String fullName,
                                @RequestParam String email,
                                @RequestParam String phone,
                                @RequestParam(required = false) String password) {
        User user = userService.findByUsername(currentUser.getUsername());
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userService.updateUser(user);
        return "redirect:/profile";
    }
}