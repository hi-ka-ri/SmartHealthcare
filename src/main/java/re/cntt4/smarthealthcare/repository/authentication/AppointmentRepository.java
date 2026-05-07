package re.cntt4.smarthealthcare.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.cntt4.smarthealthcare.entity.Appointment;
import re.cntt4.smarthealthcare.entity.Patient;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.patient.patientId = :patientId AND a.appointmentDate >= CURRENT_DATE")
    long countUpcomingAppointments(@Param("patientId") Integer patientId);

    long countByPatient(Patient patient);

    @Query("""
    SELECT a FROM Appointment a
    WHERE a.patient.patientId = :patientId
    AND a.appointmentDate >= CURRENT_DATE
    ORDER BY a.appointmentDate ASC
""")
    List<Appointment> findUpcomingByPatient(@Param("patientId") Integer patientId);

    @Query("""
    SELECT a.appointmentTime
    FROM Appointment a
    WHERE a.doctor.doctorId = :doctorId
""")
    List<LocalTime> findBookedTimesByDoctor(@Param("doctorId") Integer doctorId);

    // ================== CHỈ THÊM DÒNG NÀY ==================
    boolean existsByDoctor_DoctorIdAndAppointmentDateAndAppointmentTime(
            Integer doctorId,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );
}