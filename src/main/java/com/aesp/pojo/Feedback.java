package com.aesp.pojo;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "Feedbacks")
public class Feedback {     //phản hồi đánh giá
    //.1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;
    @Column(name = "comment")
    public String comment;
    @Column(name = "overall_score")
    public double pronunciationScore;
    @Column(name = "pronunciation_score")
    public double grammarScore; 

}
