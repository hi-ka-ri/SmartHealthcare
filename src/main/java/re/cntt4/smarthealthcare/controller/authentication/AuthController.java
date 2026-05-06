package re.cntt4.smarthealthcare.controller.authentication;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import re.cntt4.smarthealthcare.dto.authentication.RegisterRequest;
import re.cntt4.smarthealthcare.service.authentication.AuthService;
import re.cntt4.smarthealthcare.repository.authentication.UserProfileRepository;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserProfileRepository profileRepository;

    // ===== LOGIN =====
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // ===== REGISTER =====
    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("registerRequest") RegisterRequest req,
            BindingResult result
    ) {

        // check email trùng
        if (profileRepository.findByEmail(req.getEmail()).isPresent()) {
            result.rejectValue("email", "error.email", "Email đã tồn tại");
        }

        if (result.hasErrors()) {
            return "register";
        }

        authService.register(req);

        return "redirect:/login";
    }

    // ===== ROOT =====
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
}