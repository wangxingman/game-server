package com.game.see.repository;

import com.game.see.entity.MedicalBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:47 2019/7/26 0026
 * @explain :
 */
@Repository
public interface MedicalBillRepository extends JpaRepository<MedicalBill,Long>, JpaSpecificationExecutor {
    
}
