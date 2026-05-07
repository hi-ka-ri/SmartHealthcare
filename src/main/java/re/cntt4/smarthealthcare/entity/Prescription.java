package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.*;
import re.cntt4.smarthealthcare.constant.PrescriptionStatus;

import java.util.List;

@Entity
@Table(name = "prescriptions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private Integer prescriptionId;

    // 1-1
    @OneToOne
    @JoinColumn(name = "record_id", nullable = false, unique = true)
    private MedicalRecord medicalRecord;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PrescriptionStatus status;

    // 1-N
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionDetail> details;

    // N-1
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}