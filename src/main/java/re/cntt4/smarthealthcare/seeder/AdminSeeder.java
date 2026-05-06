package re.cntt4.smarthealthcare.seeder;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import re.cntt4.smarthealthcare.constant.Gender;
import re.cntt4.smarthealthcare.constant.Role;
import re.cntt4.smarthealthcare.entity.User;
import re.cntt4.smarthealthcare.entity.UserProfile;
import re.cntt4.smarthealthcare.repository.authentication.UserRepository;
import re.cntt4.smarthealthcare.repository.authentication.UserProfileRepository;

import java.time.LocalDate;

@Component
public class AdminSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seedAdmin() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPasswordHash(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setIsActive(true);
            userRepository.save(admin);

            UserProfile profile = new UserProfile();
            profile.setUser(admin);
            profile.setFullName("Quản trị viên");
            profile.setEmail("admin@smarthealth.com");
            profile.setPhone("0123456789");
            profile.setDob(LocalDate.of(1990, 1, 1));
            profile.setGender(Gender.MALE);
            profile.setAddress("Hà Nội");
            profileRepository.save(profile);
        }
    }
}
