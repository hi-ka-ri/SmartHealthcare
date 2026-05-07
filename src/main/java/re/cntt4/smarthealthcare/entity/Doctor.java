package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId;

    // 1-1
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // N-1
    @ManyToOne
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 1-N
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}