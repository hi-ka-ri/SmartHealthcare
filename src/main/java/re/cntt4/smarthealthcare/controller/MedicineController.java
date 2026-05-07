package re.cntt4.smarthealthcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import re.cntt4.smarthealthcare.dto.MedicineDTO;
import re.cntt4.smarthealthcare.entity.Medicine;
import re.cntt4.smarthealthcare.service.medicine.MedicineService;

import java.util.List;

@Controller
@RequestMapping("/admin/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    // ================= LIST =================
    @GetMapping
    public String list(Model model) {

        List<Medicine> medicines = medicineService.findAll();

        model.addAttribute("medicines", medicines);

        model.addAttribute("totalMedicines", medicines.size());

        model.addAttribute("lowStockCount",
                medicines.stream()
                        .filter(m -> m.getStockQuantity() != null
                                && m.getStockQuantity() > 0
                                && m.getStockQuantity() <= 20)
                        .count()
        );

        model.addAttribute("outOfStockCount",
                medicines.stream()
                        .filter(m -> m.getStockQuantity() != null
                                && m.getStockQuantity() == 0)
                        .count()
        );

        return "admin/medicines/list-medicine";
    }

    // ================= CREATE =================
    @GetMapping("/create")
    public String createForm(Model model) {

        model.addAttribute("medicineDTO", new MedicineDTO());

        return "admin/medicines/form-medicine";
    }

    // ================= EDIT =================
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        try {
            Medicine medicine = medicineService.findById(id);

            MedicineDTO dto = new MedicineDTO();

            dto.setMedicineId(medicine.getMedicineId());
            dto.setName(medicine.getName());
            dto.setDescription(medicine.getDescription());
            dto.setStockQuantity(medicine.getStockQuantity());
            dto.setUnit(medicine.getUnit());
            dto.setPrice(medicine.getPrice());
            dto.setManufacturer(medicine.getManufacturer());
            dto.setExpiryDate(medicine.getExpiryDate());

            model.addAttribute("medicineDTO", dto);

            return "admin/medicines/form-medicine";

        } catch (RuntimeException e) {

            redirectAttributes.addFlashAttribute("errorMessage", "Medicine not found");
            return "redirect:/admin/medicines";
        }
    }

    // ================= SAVE =================
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("medicineDTO") MedicineDTO dto,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "admin/medicines/form-medicine";
        }

        if (dto.getMedicineId() != null) {

            medicineService.update(dto.getMedicineId(), dto);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Medicine updated successfully");

        } else {

            medicineService.create(dto);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Medicine created successfully");
        }

        return "redirect:/admin/medicines";
    }

    // ================= DELETE =================
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,
                         RedirectAttributes redirectAttributes) {

        medicineService.delete(id);

        redirectAttributes.addFlashAttribute("successMessage",
                "Medicine deleted successfully");

        return "redirect:/admin/medicines";
    }

}