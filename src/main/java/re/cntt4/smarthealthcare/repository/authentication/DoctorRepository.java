package re.cntt4.smarthealthcare.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import re.cntt4.smarthealthcare.entity.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    List<Doctor> findBySpecialty_SpecialtyId(Integer specialtyId);
}