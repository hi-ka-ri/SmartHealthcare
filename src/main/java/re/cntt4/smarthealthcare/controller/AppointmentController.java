package re.cntt4.smarthealthcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import re.cntt4.smarthealthcare.dto.AppointmentDTO;
import re.cntt4.smarthealthcare.repository.authentication.DoctorRepository;
import re.cntt4.smarthealthcare.repository.authentication.SpecialtyRepository;
import re.cntt4.smarthealthcare.service.AppointmentService;

import java.security.Principal;

@Controller
@RequestMapping("/patient/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    // ================= FORM =================
    // ================= FORM =================
    @GetMapping("/new")
    public String form(
            @RequestParam(required = false) Integer specialtyId,
            @RequestParam(required = false) Integer doctorId,
            Model model
    ) {
        model.addAttribute("dto", new AppointmentDTO());
        model.addAttribute("specialties", specialtyRepository.findAll());

        if (specialtyId != null) {
            model.addAttribute("doctors",
                    doctorRepository.findBySpecialty_SpecialtyId(specialtyId)
            );
        }

        if (doctorId != null) {
            model.addAttribute("slots",
                    appointmentService.getAvailableSlots(doctorId)
            );
        }

        model.addAttribute("selectedSpecialtyId", specialtyId);
        model.addAttribute("selectedDoctorId", doctorId);

        return "patient/form-appointment"; // sửa lại
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("dto") AppointmentDTO dto,
                       BindingResult result,
                       Principal principal,
                       Model model) {

        // luôn load lại specialty
        model.addAttribute("specialties", specialtyRepository.findAll());

        // giữ lại selected state
        model.addAttribute("selectedSpecialtyId", dto.getSpecialtyId());
        model.addAttribute("selectedDoctorId", dto.getDoctorId());

        // reload doctors nếu đã chọn specialty
        if (dto.getSpecialtyId() != null) {
            model.addAttribute("doctors",
                    doctorRepository.findBySpecialty_SpecialtyId(dto.getSpecialtyId())
            );
        }

        // reload slots nếu đã chọn doctor
        if (dto.getDoctorId() != null) {
            model.addAttribute("slots",
                    appointmentService.getAvailableSlots(dto.getDoctorId())
            );
        }

        // ❗ nếu lỗi validation thì KHÔNG redirect
        if (result.hasErrors()) {
            return "patient/form-appointment";
        }

        // lỗi business (trùng lịch) nếu có thì catch
        try {
            appointmentService.createAppointment(dto, principal.getName());
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "patient/form-appointment";
        }

        return "redirect:/patient/home";
    }
}