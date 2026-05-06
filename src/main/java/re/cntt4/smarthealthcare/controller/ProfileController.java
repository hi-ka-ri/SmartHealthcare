package re.cntt4.smarthealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import re.cntt4.smarthealthcare.entity.User;
import re.cntt4.smarthealthcare.entity.UserProfile;
import re.cntt4.smarthealthcare.repository.authentication.UserProfileRepository;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private UserProfileRepository profileRepository;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        // principal.getName() chính là email đăng nhập
        UserProfile profile = profileRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ"));

        User user = profile.getUser();

        model.addAttribute("profile", profile);
        model.addAttribute("role", user.getRole().name());
        model.addAttribute("userId", user.getUserId());

        return "profile/common"; // trả về view profile/common.html
    }
}
