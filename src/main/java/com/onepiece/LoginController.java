package com.onepiece;

import com.onepiece.model.User;
import com.onepiece.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Serve the registration page
    @GetMapping("/")
    public String showLoginForm() {
        return "index";
    }

    // Handle form submission
    @PostMapping("/submit")
    @ResponseBody
    public String handleRegistration(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("photo") MultipartFile photo) {

        try {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password); // 🔐 You can hash this later
            user.setPhoto(photo.getBytes());

            userRepository.save(user);

            return "🎉 Registration successful! Welcome aboard!";
        } catch (Exception e) {
            return "⚠️ Error during registration: " + e.getMessage();
        }
    }
}

