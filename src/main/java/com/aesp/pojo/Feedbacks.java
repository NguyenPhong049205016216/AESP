package com.aesp.pojo;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "Feedbacks")
public class Feedbacks {     //phản hồi đánh giá
    //.1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "overall_score")
    private double pronunciationScore;
    @Column(name = "pronunciation_score")
    private double grammarScore; 
    @Column(name = "status")
    private String status; 

    @OneToOne
    @JoinColumn(name = "conversation_session_id")
    private ConversationSession conversationSession;

    public void setStatus(String newStatus) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }
    

}
