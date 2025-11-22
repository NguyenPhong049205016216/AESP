package com.aesp.service;
import com.aesp.pojo.Packages;
import com.aesp.repository.PackagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PackageService {

    private final PackagesRepository packagesRepository;

    @Autowired
    public PackageService(PackagesRepository packagesRepository) {
        this.packagesRepository = packagesRepository;
    }

    public List<Packages> findAllPackages() {
        return packagesRepository.findAll();
    }//láy danh sách (all gói ). dùng cho livew
    
    public Packages getPackageById(Long id) throws Exception {
        return packagesRepository.findById(id)
                .orElseThrow(() -> new Exception("Gói dịch vụ không tồn tại."));
    }// láy gói theo id
    
    @Transactional
    public Packages saveOrUpdatePackage(Packages pkg) throws Exception {
        // [Logic nghiệp vụ: Ví dụ kiểm tra giá > 0]
        if (pkg.getPrive() <= 0 && !"Miễn Phí".equalsIgnoreCase(pkg.getName())) {
             throw new Exception("Giá gói phải lớn hơn 0.");
        }
        
        // Spring Data JPA dùng save() cho cả INSERT và UPDATE
        return packagesRepository.save(pkg);
    }// tạo || cập nhập packages (save || update)

    
    @Transactional
    public void deletePackage(Long id) throws Exception {
        if (!packagesRepository.existsById(id)) {
            throw new Exception("Gói dịch vụ không tồn tại để xóa.");
        }
        packagesRepository.deleteById(id);
    }// delete pakages
}
