package com.game.see.repository.handle;

import com.game.see.entity.handle.MedicalRecycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 6:04 2019/8/13 0013
 * @explain :
 */
public interface MedicalRecycleRepository extends JpaRepository<MedicalRecycle,Long>, JpaSpecificationExecutor {
    
}
