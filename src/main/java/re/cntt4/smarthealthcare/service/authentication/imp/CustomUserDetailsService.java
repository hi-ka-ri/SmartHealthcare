package re.cntt4.smarthealthcare.service.authentication.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import re.cntt4.smarthealthcare.entity.User;
import re.cntt4.smarthealthcare.entity.UserProfile;
import re.cntt4.smarthealthcare.repository.authentication.UserProfileRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserProfile profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));

        User user = profile.getUser();

        return org.springframework.security.core.userdetails.User
                .withUsername(profile.getEmail()) // login bằng email
                .password(user.getPasswordHash())
                .roles(user.getRole().name())
                .build();
    }
}