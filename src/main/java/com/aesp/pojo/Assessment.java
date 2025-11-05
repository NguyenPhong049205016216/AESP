package com.aesp.pojo;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "Assessments")
public class Assessment {  //đánh giá

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;
    @Column(name = "score")
    public double score;
    @Column(name = "topic")
    public String topic;
    @Column(name = "feedback")
    public String feedback;
    @Column(name = "session_id")
    public String sessionId;
    //Mộ Assessment thuộc về 1 Learner:.1 đánh giá_học viên
    //Một Menter sẻ có nhiều Assessment:.1 NG_hướng dẩn_đánh giá
    @ManyToOne
    @JoinColumn(name = "learner_id") 
    public Learner learner;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    public Mentor mentor;

    public double getScore() {
        return score;
    }
    
    public void setScore(double score) {
        this.score = score;
    }
    

    

}
