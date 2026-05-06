package re.cntt4.smarthealthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.cntt4.smarthealthcare.constant.Gender;

import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileId;

    // 1-1
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(length = 100)
    private String fullName;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 20)
    private String phone;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 255)
    private String address;
}