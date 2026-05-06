package re.cntt4.smarthealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.cntt4.smarthealthcare.entity.UserProfile;
import re.cntt4.smarthealthcare.repository.authentication.UserProfileRepository;

import java.security.Principal;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private UserProfileRepository profileRepository;

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        // principal.getName() chính là email đăng nhập
        UserProfile profile = profileRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ"));

        model.addAttribute("profile", profile);
        return "patient/home";
    }
}
