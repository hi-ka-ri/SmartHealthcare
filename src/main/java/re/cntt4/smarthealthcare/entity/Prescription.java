package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.cntt4.smarthealthcare.constant.PrescriptionStatus;

import java.util.List;

@Entity
@Table(name = "prescriptions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prescriptionId;

    // 1-1
    @OneToOne
    @JoinColumn(name = "record_id", unique = true, nullable = false)
    private MedicalRecord medicalRecord;

    @Enumerated(EnumType.STRING)
    private PrescriptionStatus status;

    // 1-N
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionDetail> details;
}