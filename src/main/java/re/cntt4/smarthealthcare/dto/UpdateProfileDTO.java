package re.cntt4.smarthealthcare.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import re.cntt4.smarthealthcare.constant.Gender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileDTO {

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be less than 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(0)[0-9]{9}$",
            message = "Phone number must contain 10 digits"
    )
    private String phone;

    @NotBlank(message = "Citizen ID is required")
    @Pattern(
            regexp = "^[0-9]{12}$",
            message = "Citizen ID must contain exactly 12 digits"
    )
    private String cccd;

    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    @Size(max = 500, message = "Avatar URL is too long")
    private String avatarUrl;

    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotNull(message = "Gender is required")
    private Gender gender;
}