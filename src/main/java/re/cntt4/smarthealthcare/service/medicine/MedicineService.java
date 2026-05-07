package re.cntt4.smarthealthcare.service.medicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.cntt4.smarthealthcare.dto.MedicineDTO;
import re.cntt4.smarthealthcare.entity.Medicine;
import re.cntt4.smarthealthcare.repository.authentication.MedicineRepository;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    // ================= FIND ALL =================
    public List<Medicine> findAll() {

        return medicineRepository.findAll();
    }

    // ================= FIND BY ID =================
    public Medicine findById(Integer id) {

        return medicineRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Medicine not found"));
    }

    // ================= CREATE =================
    public Medicine create(MedicineDTO dto) {

        Medicine medicine = new Medicine();

        medicine.setName(dto.getName());
        medicine.setDescription(dto.getDescription());
        medicine.setStockQuantity(dto.getStockQuantity());
        medicine.setUnit(dto.getUnit());
        medicine.setPrice(dto.getPrice());
        medicine.setManufacturer(dto.getManufacturer());
        medicine.setExpiryDate(dto.getExpiryDate());

        return medicineRepository.save(medicine);
    }

    // ================= UPDATE =================
    public Medicine update(Integer id,
                           MedicineDTO dto) {

        Medicine medicine = findById(id);

        medicine.setName(dto.getName());
        medicine.setDescription(dto.getDescription());
        medicine.setStockQuantity(dto.getStockQuantity());
        medicine.setUnit(dto.getUnit());
        medicine.setPrice(dto.getPrice());
        medicine.setManufacturer(dto.getManufacturer());
        medicine.setExpiryDate(dto.getExpiryDate());

        return medicineRepository.save(medicine);
    }

    // ================= DELETE =================
    public void delete(Integer id) {

        medicineRepository.deleteById(id);
    }
    public List<Medicine> searchByName(String keyword) {
        return medicineRepository
                .findByNameContainingIgnoreCase(keyword);
    }
}