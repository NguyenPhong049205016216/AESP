package com.aesp.repository;
import com.aesp.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import java.util.Optional;

// Đây là Interface mà Spring sẽ tạo ra Class triển khai
@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> { // <<< ID LÀ LONG

    // Spring Data JPA sẽ tự động triển khai hàm này:
    Optional<User> findByEmail(String email); 
    
    // Spring sẽ tự động cung cấp: save(), findById(), findAll(), deleteById(), v.v.
}
