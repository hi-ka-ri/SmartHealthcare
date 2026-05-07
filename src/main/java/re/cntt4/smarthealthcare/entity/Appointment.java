package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.*;
import re.cntt4.smarthealthcare.constant.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(
        name = "appointments",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "doctor_id",
                        "appointment_date",
                        "appointment_time"
                }
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Integer appointmentId;

    // N-1
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // N-1
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    // N-1
    @ManyToOne
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "appointment_time", nullable = false)
    private LocalTime appointmentTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppointmentStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 1-1
    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}