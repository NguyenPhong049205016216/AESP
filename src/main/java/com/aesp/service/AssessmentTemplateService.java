package com.aesp.service;
import com.aesp.pojo.AssessmentTemplate;
import com.aesp.repository.AssessmentTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssessmentTemplateService {

    private final AssessmentTemplateRepository templateRepository;

    @Autowired
    public AssessmentTemplateService(AssessmentTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }
    //Logic nghiệp vụ để tạo một mẫu bài tập mới.
    @Transactional
    public AssessmentTemplate createTemplate(String name, String contentJson, String difficulty) throws Exception {
        
        // 1. Kiểm tra: Tên Template đã tồn tại chưa
        if (templateRepository.findByName(name).isPresent()) {
            throw new Exception("Tên mẫu bài tập đã tồn tại. Vui lòng chọn tên khác.");
        }
        AssessmentTemplate newTemplate = new AssessmentTemplate();
        newTemplate.setName(name);
        newTemplate.setContentJson(contentJson); 
        newTemplate.setDifficultyLevel(difficulty);
        // 3. Lưu vào CSDL
        return templateRepository.save(newTemplate);
    }
    public java.util.List<AssessmentTemplate> findAllTemplates() {
        return templateRepository.findAll();
    }
}
