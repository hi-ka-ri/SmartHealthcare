package re.cntt4.smarthealthcare.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.cntt4.smarthealthcare.entity.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p FROM Patient p WHERE p.user.profile.email = :email")
    Optional<Patient> findByUserEmail(@Param("email") String email);
}
