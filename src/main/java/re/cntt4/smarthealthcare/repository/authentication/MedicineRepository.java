package re.cntt4.smarthealthcare.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import re.cntt4.smarthealthcare.entity.Medicine;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    // có thể thêm query tuỳ ý, ví dụ:
    // Optional<Medicine> findByName(String name);
    List<Medicine> findByNameContainingIgnoreCase(String name);
}
