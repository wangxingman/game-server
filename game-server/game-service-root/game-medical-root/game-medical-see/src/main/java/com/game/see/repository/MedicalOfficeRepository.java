package com.game.see.repository;

import com.game.see.entity.MedicalDoctor;
import com.game.see.entity.MedicalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:25 2019/7/26 0026
 * @explain :
 */
@Repository
public interface MedicalOfficeRepository extends JpaRepository<MedicalOffice, Long>, JpaSpecificationExecutor {
    
}
