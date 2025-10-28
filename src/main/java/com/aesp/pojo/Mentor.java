package com.aesp.pojo;

import java.util.List;

public class Mentor extends User{
   // củng kế thừa nhưng không có contructor lớp cha:.1
   public Mentor(long id, String name, String email){
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
   public List<ConversationSession> conversationSession;
}
