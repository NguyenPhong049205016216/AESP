# AESP_Java0216
AESP_Dazava0362(plask api zava)
//#Mối quang hệ aesp
1. Mối quan hệ Kế thừa (Cha-Con)
_ User & Admin:ột-Một. Một Admim chính là mộ User. Khóa chính của Admin cũng là khóa ngoại trừ đến User.
_ User & Mentor:Mối quan hệ Một-Một. Một Mentor chính là một User.
_ User & Learner:Mối quan hệ Một-Một. Một  Learner chính là một User.
3. Quan hệ của Người học và Người cố vấn
_ Learner & LearningPath:Mối quan hệ (Một-Một). Mỗi Learner có một LearningPath duy nhất. (ngược lại)
_ Learner *  Feedback:Mối quan hệ (Nhiều-Nhiều) Một Learner có thể có nhiều Feedback. (ngược lại)
_ Mentor & Feedback:Mối quan hệ (Nhiều-Nhiều) Một Mentor có thể thực hiện nhiều Feedback. ( ngược lại)
Learner và ConversationSessions:Mối quan hệ (Nhiều-Nhiều) Một Learner tham gia nhiều ConverstionSession. (
Mentor và ConversationSessions:Mối quan hệ (Nhiều-Nhiều) Một Mentor dẫn tổ chức nhiều ConversationSession.
5. Lương Mua hàng và Thanh toán
Learner và Purchase:Mối quan hệ Một-Nhiều. Một learner có thể có nhiều Purchase.
Packages và Purchase:Mối quan hệ Một-Nhiều. Một gói học tập có thể purchase nhiều lần (trong các lược purchase khác nhau).
Purchase và Payment :Mối quan hệ Một-Một. Mỗi Mua hỉ có một Sự chi trảt ương ứng.
