package com.aesp.pojo;
import jakarta.persistence.*;
import java.util.List;

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
    
}
