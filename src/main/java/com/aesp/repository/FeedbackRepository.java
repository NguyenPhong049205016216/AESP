package com.aesp.repository;

import com.aesp.pojo.Feedbacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedbacks, Long> {
    
    List<Feedbacks> findAllByStatus(String status);

    long countByStatus(String status);

}// Hàm Custom Finder để lấy danh sách cần kiểm duyệt
