package com.aesp.service;
import com.aesp.pojo.AssessmentTemplate;
import com.aesp.productinventory.exception.ResourceNotFoundException;
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
    @Transactional
    public AssessmentTemplate createTemplate(String name, String contentJson, String difficulty) throws Exception {
        
        if (templateRepository.findByName(name).isPresent()) {
            throw new Exception("Tên mẫu bài tập đã tồn tại. Vui lòng chọn tên khác.");
        }
        AssessmentTemplate newTemplate = new AssessmentTemplate();
        newTemplate.setName(name);
        newTemplate.setContentJson(contentJson); 
        newTemplate.setDifficultyLevel(difficulty);
       
        return templateRepository.save(newTemplate);
    }
    /**
     * [MỚI] Lấy một mẫu bài tập theo ID.
     * @param id ID của mẫu bài tập.
     * @return AssessmentTemplate
     * @throws ResourceNotFoundException nếu không tìm thấy.
     */
    public AssessmentTemplate findTemplateById(Long id) { 
        return templateRepository.findById(id)
                // Đảm bảo bạn có class ResourceNotFoundException hoặc dùng RuntimeException
                .orElseThrow(() -> new ResourceNotFoundException("Mẫu bài tập không tồn tại."));
    }
    public java.util.List<AssessmentTemplate> findAllTemplates() {
        return templateRepository.findAll();
    }

}// quảng lý mãu bài tập
