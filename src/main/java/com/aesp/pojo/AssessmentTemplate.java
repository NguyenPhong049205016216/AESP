package com.aesp.pojo;
import jakarta.persistence.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "AssessmentTemplates")
public class AssessmentTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;        
    
    @Column(length = 1000)
    private String contentJson;   
    
    private String difficultyLevel; 
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL)
    private List<Assessment> assessments;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContentJson() { return contentJson; }
    public void setContentJson(String contentJson) { this.contentJson = contentJson; }
    public String getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(String difficultyLevel) { this.difficultyLevel = difficultyLevel; }
    public List<Assessment> getAssessments() { return assessments; }
    public void setAssessments(List<Assessment> assessments) { this.assessments = assessments; }
    public void setQuestionsList(List<Map<String, Object>> questions) {
        try {
            this.contentJson = objectMapper.writeValueAsString(questions);
        } catch (Exception e) {
            System.err.println("Lỗi chuyển đổi List thành JSON string: " + e.getMessage());
            this.contentJson = null;
        }
    }
    /**
     * Phương thức tiện ích để chuyển đổi chuỗi JSON thành danh sách câu hỏi
     * @return Danh sách các Map/Object đại diện cho câu hỏi
     */
    public List<Map<String, Object>> getQuestionsList() {
        if (this.contentJson == null || this.contentJson.trim().isEmpty()) {
            return List.of();
        }
        try {
            // Chuyển đổi JSON string thành List of Maps
            return objectMapper.readValue(this.contentJson, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            // Xử lý lỗi (ví dụ: log lỗi và trả về danh sách rỗng)
            System.err.println("Lỗi phân tích JSON trong contentJson: " + e.getMessage());
            return List.of();
        }
    }
    
}
