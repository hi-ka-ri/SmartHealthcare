package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "specialties")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specialtyId;

    private String name;
    private String description;

    // 1-N
    @OneToMany(mappedBy = "specialty")
    private List<Doctor> doctors;
}