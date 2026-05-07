package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    // 1-1
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "cccd", unique = true, length = 20)
    private String cccd;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 1-N
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}