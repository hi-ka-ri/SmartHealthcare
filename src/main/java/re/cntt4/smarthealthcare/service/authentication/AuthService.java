package re.cntt4.smarthealthcare.service.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.cntt4.smarthealthcare.constant.Gender;
import re.cntt4.smarthealthcare.constant.Role;
import re.cntt4.smarthealthcare.dto.authentication.RegisterRequest;
import re.cntt4.smarthealthcare.entity.Patient;
import re.cntt4.smarthealthcare.entity.User;
import re.cntt4.smarthealthcare.entity.UserProfile;
import re.cntt4.smarthealthcare.repository.authentication.PatientRepository;
import re.cntt4.smarthealthcare.repository.authentication.UserProfileRepository;
import re.cntt4.smarthealthcare.repository.authentication.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository profileRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequest req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }
        if (profileRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        // check confirm password
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu không khớp");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword())); // hash password
        user.setRole(Role.PATIENT);
        user.setIsActive(true);

        userRepository.save(user);
        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setFullName(req.getFullName());
        profile.setPhone(req.getPhone());
        profile.setEmail(req.getEmail());
        profile.setDob(req.getDob());
        if (req.getGender() != null && !req.getGender().isEmpty()) {
            profile.setGender(Gender.valueOf(req.getGender()));
        }
        profile.setAddress(req.getAddress());

        profileRepository.save(profile);
        if (user.getRole() == Role.PATIENT) {
            Patient patient = new Patient();
            patient.setUser(user);
            patient.setInsuranceNumber(null);
            patientRepository.save(patient);
        }
    }
}
