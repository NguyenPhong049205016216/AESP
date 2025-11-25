package com.aesp.repository;
import com.aesp.pojo.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackagesRepository extends JpaRepository<Packages, Long> {
    Optional<Packages> findByName(String name);
    
}// Spring Data JPA sẽ cung cấp save(), findAll(), findById(), delete() tự động.
