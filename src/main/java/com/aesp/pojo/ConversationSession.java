package com.aesp.pojo;
import java.util.Date;
public class ConversationSession{
    //.1
    public long id;
    public String topic;
    public Date startTime;
    public int duration;
    public boolean aiSupport;
    //khai báo vì quang hệ: Bắc buộc .1
    public Learner learner;
    //Khai báo vì quang hệ: Bắc buộc  .1
    public Mentor mentor;
    
}