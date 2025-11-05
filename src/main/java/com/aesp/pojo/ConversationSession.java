package com.aesp.pojo;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "ConversationSessions")
public class ConversationSession{   //buổi hội thoại
    //.1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;
    @Column(name = "topic")
    public String topic;
    @Column(name = "start_time")
    public Date startTime;
    @Column(name = "end_time")
    public int duration;
    @Column(name = "ai_support")
    public boolean aiSupport;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    //khai báo vì quang hệ: Bắc buộc .1
    public Learner learner;
    
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    //Khai báo vì quang hệ: Bắc buộc  .1
    public Mentor mentor;
    
}