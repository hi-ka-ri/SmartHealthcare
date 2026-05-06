package re.cntt4.smarthealthcare.repository.authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import re.cntt4.smarthealthcare.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
