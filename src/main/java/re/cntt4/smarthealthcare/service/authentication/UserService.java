package re.cntt4.smarthealthcare.service.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.cntt4.smarthealthcare.entity.User;
import re.cntt4.smarthealthcare.entity.UserProfile;
import re.cntt4.smarthealthcare.repository.authentication.UserProfileRepository;

@Service
public class UserService {

    @Autowired
    private UserProfileRepository profileRepository;

    public UserProfile getProfile(User user) {
        return profileRepository.findAll()
                .stream()
                .filter(p -> p.getUser().getUserId().equals(user.getUserId()))
                .findFirst()
                .orElse(null);
    }
}