package com.aesp.service;

import com.aesp.pojo.Learner;
import com.aesp.pojo.Mentor;
import com.aesp.repository.LearnerRepository;
import com.aesp.repository.UserRepository; // Để dùng cho các chức năng chung
import com.aesp.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
 
@Service
public class LearnerManagementService {

    private final LearnerRepository learnerRepository;
    private final UserRepository userRepository; 
    private final MentorRepository mentorRepository;

    @Autowired
    public LearnerManagementService(LearnerRepository learnerRepository, UserRepository userRepository, 
        MentorRepository mentorRepository) {
        this.learnerRepository = learnerRepository;
        this.userRepository = userRepository;
        this.mentorRepository = mentorRepository;
    }
    public List<Learner> findLearnersByMentorId(Long mentorId) {
        return learnerRepository.findAllByMentorId(mentorId); 
    }
    
    public Learner findLearnerById(Long learnerId) throws Exception {
         return learnerRepository.findById(learnerId)
        .orElseThrow(() -> new Exception("Học viên không tồn tại."));
    }
    
    @Transactional
public void assignMentorToLearner(Long learnerId, Long mentorId) throws Exception {
    Learner learner = learnerRepository.findById(learnerId)
    .orElseThrow(() -> new Exception("Học viên không tồn tại."));
    
    Mentor mentor = null;
    if (mentorId != null) {
         mentor = mentorRepository.findById(mentorId)
        .orElseThrow(() -> new Exception("Mentor không tồn tại."));
    }
    
    learner.setMentor(mentor); 
    learnerRepository.save(learner);
}
    
    public Object getProgressReport(Long learnerId) {
        return "Report data for learner: " + learnerId;
    }
}
