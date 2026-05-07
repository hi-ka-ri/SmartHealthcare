package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "medicines")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Integer medicineId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 1-N
    @OneToMany(mappedBy = "medicine")
    private List<PrescriptionDetail> details;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}