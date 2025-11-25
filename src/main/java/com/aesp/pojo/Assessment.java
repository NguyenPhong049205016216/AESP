package com.aesp.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "Assessments")
public class Assessment {  //đánh giá

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "score")
    public double score;
    @Column(name = "topic")
    public String topic;
    @Column(name = "feedback")
    public String feedback;
    @Column(name = "session_id")
    public String sessionId;
    @Column(name = "status")
    public String status;
    @Column(name = "custom_content_json", length = 4000) // Tăng độ dài nếu cần
    private String customContentJson; 
    //Mộ Assessment thuộc về 1 Learner:.1 đánh giá_học viên
    //Một Menter sẻ có nhiều Assessment:.1 NG_hướng dẩn_đánh giá
    @ManyToOne
    @JoinColumn(name = "learner_id") 
    private Learner learner;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false) // Khóa ngoại template_id
    private AssessmentTemplate template;

    public double getScore() {
        return score;
    }
    
    public void setScore(double score) {
        this.score = score;
    }
    public Assessment() {
    }

    // ===============================================================
    // GETTERS & SETTERS (Đã thêm các hàm còn thiếu)
    // ===============================================================
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public AssessmentTemplate getTemplate() { return template; }
    public void setTemplate(AssessmentTemplate template) { this.template = template; }
    
    public Learner getLearner() { return learner; }
    public void setLearner(Learner learner) { this.learner = learner; }
    
    public Mentor getMentor() { return mentor; }
    public void setMentor(Mentor mentor) { this.mentor = mentor; }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public String getCustomContentJson() 
    { 
        return customContentJson; 
    }
    public void setCustomContentJson(String customContentJson) 
    { 
        this.customContentJson = customContentJson; 
    }
    @Override
    public String toString() {
        return "Assessment [id=" + id + ", score=" + score + ", topic=" + topic + ", status=" + status + "]";
    }
}
