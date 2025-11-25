package com.aesp.service;

import com.aesp.pojo.Feedbacks;
import com.aesp.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackService {
    
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    // 1. Lấy danh sách cần kiểm duyệt (IMPLEMENTATION)
    public List<Feedbacks> findPendingFeedback() {
        // Trạng thái 'PENDING' là do bạn tự định nghĩa trong logic
        return feedbackRepository.findAllByStatus("PENDING"); 
    }
    // 2. Cập nhật trạng thái Feedback (APPROVE/REJECT)
    @Transactional
    public void updateFeedbackStatus(Long feedbackId, String newStatus) throws Exception {
        Feedbacks feedback = feedbackRepository.findById(feedbackId)
        .orElseThrow(() -> new Exception("Feedback không tồn tại."));
        
        feedback.setStatus(newStatus);
        feedbackRepository.save(feedback);
    }
    
    // 3. Xóa Feedback
    @Transactional
    public void deleteFeedback(Long feedbackId) throws Exception {
        if (!feedbackRepository.existsById(feedbackId)) {
            throw new Exception("Feedback không tồn tại để xóa.");
        }
        feedbackRepository.deleteById(feedbackId);
    }
}
