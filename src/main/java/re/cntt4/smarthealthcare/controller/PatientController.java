package re.cntt4.smarthealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.cntt4.smarthealthcare.entity.Appointment;
import re.cntt4.smarthealthcare.entity.Patient;
import re.cntt4.smarthealthcare.entity.UserProfile;
import re.cntt4.smarthealthcare.repository.authentication.AppointmentRepository;
import re.cntt4.smarthealthcare.repository.authentication.PatientRepository;
import re.cntt4.smarthealthcare.repository.authentication.PrescriptionRepository;
import re.cntt4.smarthealthcare.repository.authentication.UserProfileRepository;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        Patient patient = patientRepository.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân"));

        long upcomingAppointments = appointmentRepository.countUpcomingAppointments(patient.getPatientId());
        long totalAppointments = appointmentRepository.countByPatient(patient);
        long activePrescriptions = prescriptionRepository.countActiveByPatient(patient.getPatientId());

        List<Appointment> appointments = appointmentRepository.findUpcomingByPatient(patient.getPatientId());

        model.addAttribute("profile", patient.getUser().getProfile());
        model.addAttribute("upcomingAppointments", upcomingAppointments);
        model.addAttribute("totalAppointments", totalAppointments);
        model.addAttribute("activePrescriptions", activePrescriptions);
        model.addAttribute("appointments", appointments);

        return "patient/home";
    }
}

