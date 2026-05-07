package re.cntt4.smarthealthcare.repository.authentication;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.cntt4.smarthealthcare.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
    @Query("SELECT COUNT(p) FROM Prescription p WHERE p.patient.patientId = :patientId AND p.isActive = true")
    long countActiveByPatient(@Param("patientId") Integer patientId);
}
