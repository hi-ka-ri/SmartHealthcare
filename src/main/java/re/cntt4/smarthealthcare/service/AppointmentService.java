package re.cntt4.smarthealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.cntt4.smarthealthcare.constant.AppointmentStatus;
import re.cntt4.smarthealthcare.dto.AppointmentDTO;
import re.cntt4.smarthealthcare.entity.*;
import re.cntt4.smarthealthcare.repository.authentication.*;

import java.util.Arrays;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    // ================= CREATE =================
    public void createAppointment(AppointmentDTO dto, String email) {

        Patient patient = patientRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Specialty specialty = specialtyRepository.findById(dto.getSpecialtyId())
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        // ===== CHECK TRÙNG LỊCH (FIX CHUẨN DB) =====
        boolean exists = appointmentRepository
                .existsByDoctor_DoctorIdAndAppointmentDateAndAppointmentTime(
                        dto.getDoctorId(),
                        dto.getAppointmentDate(),
                        dto.getAppointmentTime()
                );

        if (exists) {
            throw new RuntimeException("Khung giờ đã có người đặt");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setSpecialty(specialty);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(AppointmentStatus.PENDING);

        appointmentRepository.save(appointment);
    }

    // ================= SLOT (LOẠI GIỜ ĐÃ BOOK) =================
    public List<String> getAvailableSlots(Integer doctorId) {

        List<java.time.LocalTime> booked = appointmentRepository
                .findBookedTimesByDoctor(doctorId);

        List<String> allSlots = List.of(
                "08:00", "09:00", "10:00",
                "14:00", "15:00", "16:00"
        );

        return allSlots.stream()
                .filter(slot -> booked.stream()
                        .noneMatch(b -> b.toString().startsWith(slot)))
                .toList();
    }
}