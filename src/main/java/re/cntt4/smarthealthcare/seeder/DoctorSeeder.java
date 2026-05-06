package re.cntt4.smarthealthcare.seeder;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import re.cntt4.smarthealthcare.constant.Gender;
import re.cntt4.smarthealthcare.constant.Role;
import re.cntt4.smarthealthcare.entity.Doctor;
import re.cntt4.smarthealthcare.entity.Specialty;
import re.cntt4.smarthealthcare.entity.User;
import re.cntt4.smarthealthcare.entity.UserProfile;
import re.cntt4.smarthealthcare.repository.authentication.DoctorRepository;
import re.cntt4.smarthealthcare.repository.authentication.SpecialtyRepository;
import re.cntt4.smarthealthcare.repository.authentication.UserRepository;
import re.cntt4.smarthealthcare.repository.authentication.UserProfileRepository;

import java.time.LocalDate;

@Component
public class DoctorSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository profileRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seedDoctors() {
        Specialty specialty = specialtyRepository.findById(1)
                .orElseGet(() -> {
                    Specialty sp = new Specialty();
                    sp.setName("Nội tổng quát");
                    sp.setDescription("Khám chữa bệnh tổng quát");
                    return specialtyRepository.save(sp);
                });

        createDoctor("doctor1", "doctor1@smarthealth.com", "Bác sĩ Nguyễn Văn A", specialty, "LIC001", 10);
        createDoctor("doctor2", "doctor2@smarthealth.com", "Bác sĩ Trần Thị B", specialty, "LIC002", 8);
        createDoctor("doctor3", "doctor3@smarthealth.com", "Bác sĩ Lê Văn C", specialty, "LIC003", 12);
        createDoctor("doctor4", "doctor4@smarthealth.com", "Bác sĩ Phạm Thị D", specialty, "LIC004", 7);
        createDoctor("doctor5", "doctor5@smarthealth.com", "Bác sĩ Hoàng Văn E", specialty, "LIC005", 15);
    }

    private void createDoctor(String username, String email, String fullName,
                              Specialty specialty, String license, int expYears) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User doctorUser = new User();
            doctorUser.setUsername(username);
            doctorUser.setPasswordHash(passwordEncoder.encode("doctor123"));
            doctorUser.setRole(Role.DOCTOR);
            doctorUser.setIsActive(true);
            userRepository.save(doctorUser);

            UserProfile profile = new UserProfile();
            profile.setUser(doctorUser);
            profile.setFullName(fullName);
            profile.setEmail(email);
            profile.setPhone("0900000000");
            profile.setDob(LocalDate.of(1980, 1, 1));
            profile.setGender(Gender.MALE);
            profile.setAddress("Hà Nội");
            profileRepository.save(profile);

            Doctor doctor = new Doctor();
            doctor.setUser(doctorUser);
            doctor.setSpecialty(specialty);
            doctor.setLicenseNumber(license);
            doctor.setExperienceYears(expYears);
            doctorRepository.save(doctor);
        }
    }
}
