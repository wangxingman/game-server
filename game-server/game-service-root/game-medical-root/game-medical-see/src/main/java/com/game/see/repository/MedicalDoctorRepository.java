package com.game.see.repository;

import com.game.see.entity.MedicalDoctor;
import com.game.see.entity.MedicalRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:29 2019/7/25 0025
 * @explain :
 */
@Repository
public interface MedicalDoctorRepository extends JpaRepository<MedicalDoctor,Long>, JpaSpecificationExecutor {
    
}
