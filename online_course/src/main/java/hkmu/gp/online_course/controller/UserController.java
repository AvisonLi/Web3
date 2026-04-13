package hkmu.gp.online_course.controller;

import hkmu.gp.online_course.dto.UserDto;
import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;   // 用于国际化

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
    public String register(@Valid @ModelAttribute("user") UserDto dto,
                           BindingResult result,
                           @RequestParam String role,
                           HttpServletRequest request,
                           Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.register(dto, "ROLE_" + role.toUpperCase());
            return "redirect:/login";
        } catch (DataIntegrityViolationException e) {
            Locale locale = RequestContextUtils.getLocale(request);
            String errorMsg = messageSource.getMessage("error.username.exists", null, locale);
            model.addAttribute("error", errorMsg);
            model.addAttribute("user", dto);
            return "register";
        }
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