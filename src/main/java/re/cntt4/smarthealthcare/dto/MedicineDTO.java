package re.cntt4.smarthealthcare.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {

    private Integer medicineId;

    @NotBlank(message = "Tên thuốc không được để trống")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải >= 0")
    private Integer stockQuantity;

    @NotBlank(message = "Đơn vị không được để trống")
    private String unit;

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải > 0")
    private Double price;

    @NotBlank(message = "Hãng sản xuất không được để trống")
    private String manufacturer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
}