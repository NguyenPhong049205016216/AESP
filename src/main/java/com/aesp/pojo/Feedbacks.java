package com.aesp.pojo;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "Feedbacks")
public class Feedbacks {     //phản hồi đánh giá
    //.1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "comment")
    public String comment;
    @Column(name = "overall_score")
    public double pronunciationScore;
    @Column(name = "pronunciation_score")
    public double grammarScore; 

    @OneToOne
    @JoinColumn(name = "conversation_session_id")
    private ConversationSession conversationSession;
    

}
