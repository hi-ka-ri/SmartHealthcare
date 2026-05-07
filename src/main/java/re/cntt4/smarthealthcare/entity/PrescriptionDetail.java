package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prescription_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrescriptionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer detailId;

    // N-1
    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    // N-1
    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "dosage")
    private String dosage;
}