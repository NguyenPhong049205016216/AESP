package com.aesp.repository;
import com.aesp.pojo.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackagesRepository extends JpaRepository<Packages, Long> {
    
    // Spring Data JPA sẽ cung cấp save(), findAll(), findById(), delete() tự động.
    
    // Tùy chọn: Hàm để kiểm tra tên gói có trùng không (cho chức năng tạo gói)
    Optional<Packages> findByName(String name);
}
