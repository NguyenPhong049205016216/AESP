package com.aesp.controller;
import com.aesp.repository.IUserRepository; // Nên dùng Interface
import com.aesp.repository.UserRepository;
import com.aesp.pojo.Learner;
import com.aesp.pojo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

// Đặt tên class rõ ràng hơn
public class Ctroller_Register extends HttpServlet {
    
    private IUserRepository userRepository;

    @Override
    public void init() {
        this.userRepository = new UserRepository();
    }

    /**
     * Phương thức GET: Chỉ dùng để hiển thị trang register.jsp
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Chuyển tiếp request đến file JSP để hiển thị form
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Phương thức POST: Xử lý dữ liệu khi người dùng nhấn nút "Đăng ký"
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        // 1. Lấy dữ liệu từ form
        String name = request.getParameter("name");
        String email = request.getParameter("con");
        String password = request.getParameter("password");
        // Lấy thêm các trường khác nếu có, ví dụ:
        // String goal = request.getParameter("goal");

        // 2. Kiểm tra email đã tồn tại chưa
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            request.setAttribute("errorMessage", "Email này đã được sử dụng.");
            doGet(request, response); // Quay lại trang đăng ký và hiển thị lỗi
            return;
        }
        
        // 3. Tạo đối tượng Learner
        Learner newLearner = new Learner();
        newLearner.setName(name);
        newLearner.setEmail(email); // SỬA LẠI TÊN PHƯƠNG THỨC
        newLearner.setPassword(password); // Cần mã hóa mật khẩu này
        newLearner.setRole("LEARNER");
        newLearner.setStatus(true);
        // newLearner.setGoal(goal);

        // 4. Gọi Repository để lưu
        Learner savedLearner = userRepository.addLearner(newLearner);

        /* 5. Kiểm tra kết quả và chuyển hướng
        if (savedLearner != null && savedLearner.getId() != null) {

            // Nếu thành công, chuyển hướng đến trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            // Nếu thất bại, quay lại trang đăng ký với thông báo lỗi chung
            request.setAttribute("errorMessage", "Đã có lỗi xảy ra. Vui lòng thử lại.");
            doGet(request, response);
        }
            */
    }
}