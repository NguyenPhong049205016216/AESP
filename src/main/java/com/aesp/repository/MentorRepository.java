package com.aesp.repository;

import com.aesp.pojo.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findAllByStatus(String status); 
    
}// Nếu bạn cần lấy danh sách Mentor đang Active:
    

