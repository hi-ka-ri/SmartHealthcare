package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "specialties")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialty_id")
    private Integer specialtyId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    // 1-N
    @OneToMany(mappedBy = "specialty")
    private List<Doctor> doctors;
}