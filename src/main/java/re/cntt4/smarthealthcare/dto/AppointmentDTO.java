package re.cntt4.smarthealthcare.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDTO {

    @NotNull(message = "Vui lòng chọn chuyên khoa")
    private Integer specialtyId;

    @NotNull(message = "Vui lòng chọn bác sĩ")
    private Integer doctorId;

    @NotNull(message = "Vui lòng chọn ngày khám")
    @FutureOrPresent(message = "Ngày khám phải từ hôm nay trở đi")
    private LocalDate appointmentDate;

    @NotNull(message = "Vui lòng chọn giờ khám")
    private LocalTime appointmentTime;
}