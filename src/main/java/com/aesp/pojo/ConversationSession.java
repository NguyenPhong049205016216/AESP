package com.aesp.pojo;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "ConversationSessions")
public class ConversationSession{   //buổi hội thoại
    //.1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "topic")
    private String topic;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private int duration;
    @Column(name = "ai_support")
    private boolean aiSupport;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    //khai báo vì quang hệ: Bắc buộc .1
    public Learner learner;
    
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    //Khai báo vì quang hệ: Bắc buộc  .1
    public Mentor mentor;
    
    @OneToOne(mappedBy = "conversationSession", cascade = CascadeType.ALL)
    public Feedbacks feedbacks;
}