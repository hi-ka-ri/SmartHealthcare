package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "medicines")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicineId;

    private String name;
    private String description;
    private Integer stockQuantity;
    private String unit;
    private Double price;

    // 1-N
    @OneToMany(mappedBy = "medicine")
    private List<PrescriptionDetail> details;
}