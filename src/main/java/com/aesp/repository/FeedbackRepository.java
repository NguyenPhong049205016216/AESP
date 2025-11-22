package com.aesp.repository;

import com.aesp.pojo.Feedbacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedbacks, Long> {
    
    // Hàm Custom Finder để lấy danh sách cần kiểm duyệt
    List<Feedbacks> findAllByStatus(String status);
}
