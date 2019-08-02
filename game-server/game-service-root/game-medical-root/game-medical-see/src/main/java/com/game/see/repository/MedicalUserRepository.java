package com.game.see.repository;

import com.game.see.entity.MedicalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:46 2019/7/2 0002
 * @explain :
 */
@Repository
public interface MedicalUserRepository extends JpaRepository<MedicalUser,Long>, JpaSpecificationExecutor {
    
}
