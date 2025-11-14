<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- <<< BẮT BUỘC --%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký - AESP</title>
    
    <!-- SỬA ĐƯỜNG DẪN CSS CHO ĐÚNG CHUẨN SPRING BOOT -->
    <!-- Spring Boot không khuyến khích contextPath vì nó thường là / -->
    <!-- Bạn nên đặt main.css trong src/main/resources/static/ -->
    <link rel="stylesheet" href="/aesp/main.css"> <!-- Giả định context path là /aesp -->
    
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">
    
    <style>
        .error-message {
            color: #e74c3c;
            background-color: #fdd;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 15px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="auth-container">
        <div class="auth-box">
            <div class="auth-header">
                <div class="logo">AESP</div>
                <h2>Tạo tài khoản mới</h2>
                <p>Bắt đầu hành trình chinh phục tiếng Anh của bạn ngay hôm nay!</p>
            </div>

            <!-- HIỂN THỊ THÔNG BÁO LỖI -->
            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
            </c:if>

            <!-- FORM GỬI ĐẾN /register (ĐÚNG) -->
            <form action="${pageContext.request.contextPath}/register" method="post" class="auth-form">
                
                <!-- CÁC TRƯỜNG INPUT (Đúng tên name) -->
                <div class="form-group">
                    <label for="fullname">Họ và tên</label>
                    <input type="text" id="fullname" name="name" placeholder="Nhập họ và tên của bạn" required value="${enteredName}">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="Nhập email của bạn" required value="${enteredEmail}">
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" id="password" name="password" placeholder="Tạo mật khẩu" required>
                </div>
                 <div class="form-group">
                    <label for="confirm-password">Xác nhận mật khẩu</label>
                    <input type="password" id="confirm-password" name="confirm-password" placeholder="Nhập lại mật khẩu" required>
                </div>

                <div class="form-group">
                    <label for="goal">Mục tiêu học tập (nếu bạn là Người học)</label>
                    <input type="text" id="goal" name="goal" placeholder="Ví dụ: Tự tin giao tiếp khi đi du lịch">
                </div>
                
                <button type="submit" class="btn btn-primary">Đăng ký</button>
            </form>
            <div class="auth-footer">
                <p>Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập ngay</a></p>
            </div>
        </div>
    </div>
</body>
</html>