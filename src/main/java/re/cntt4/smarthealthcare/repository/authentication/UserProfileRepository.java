package re.cntt4.smarthealthcare.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import re.cntt4.smarthealthcare.entity.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    Optional<UserProfile> findByEmail(String email);

}