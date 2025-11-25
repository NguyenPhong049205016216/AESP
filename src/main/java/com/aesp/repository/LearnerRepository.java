package com.aesp.repository;
import java.util.List;
import com.aesp.pojo.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long>{

    List<Learner> findAllByMentorId(Long mentorId);
    
}
