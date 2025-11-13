package com.aesp.pojo;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Mentor")
//liên kết khóa chính với bảng cha User
@PrimaryKeyJoinColumn(name = "id")
public class Mentor extends User{
   public Mentor(){
    super();
   }
   // củng kế thừa nhưng không có contructor lớp cha:.1
   public Mentor(long id, String name, String email){      //nguoi hướng dẫn
   super(id, name, email);
   }

   public void evaluatilearner(){
    return;
   }

   public void giveFeedback(){
    return;
   }

   public void sharExperience(){
    return;
   }
   
   @OneToMany(mappedBy = "mentor")
   private List<ConversationSession> conversationSession;
   @OneToMany(mappedBy = "mentor")
   private List<Assessment> assessment;
}
