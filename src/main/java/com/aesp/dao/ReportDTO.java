package com.aesp.dao;

// DTO để chuyển dữ liệu tổng hợp từ Service sang Controller
public class ReportDTO {
    private long totalStudents;
    private long totalMentors;
    private long completedAssessments; // Bài tập hoàn thành
    private long pendingFeedbacks;     // Phản hồi cần kiểm duyệt

    // Constructors
    public ReportDTO() {}

    public ReportDTO(long totalStudents, long totalMentors, long completedAssessments, long pendingFeedbacks) {
        this.totalStudents = totalStudents;
        this.totalMentors = totalMentors;
        this.completedAssessments = completedAssessments;
        this.pendingFeedbacks = pendingFeedbacks;
    }

    // Getters and Setters
    public long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(long totalStudents) { this.totalStudents = totalStudents; }
    public long getTotalMentors() { return totalMentors; }
    public void setTotalMentors(long totalMentors) { this.totalMentors = totalMentors; }
    public long getCompletedAssessments() { return completedAssessments; }
    public void setCompletedAssessments(long completedAssessments) { this.completedAssessments = completedAssessments; }
    public long getPendingFeedbacks() { return pendingFeedbacks; }
    public void setPendingFeedbacks(long pendingFeedbacks) { this.pendingFeedbacks = pendingFeedbacks; }
}
