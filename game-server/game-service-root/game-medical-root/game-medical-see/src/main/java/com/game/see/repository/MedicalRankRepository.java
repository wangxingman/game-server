package com.game.see.repository;

import com.game.see.entity.MedicalRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:46 2019/7/2 0002
 * @explain :
 */
@Repository
public interface MedicalRankRepository extends JpaRepository<MedicalRank,Long>, JpaSpecificationExecutor {

    /**
     * @Author: wx
     * @Date  : 下午 4:27 2019/8/8 0008
     * @params:
     * @Desc  :
     */
    MedicalRank findBySchedulingTimeAndOfficeName(Timestamp timestamp, String officeTime);
    
    /**
     * @Author: wx
     * @Date  : 上午 11:12 2019/8/9 0009 
     * @params: 
     * @Desc  :  通过编号获取当前数据
     */
    MedicalRank findByOrderNumber(String orderNumber);
    
    /**
     * @Author: wx
     * @Date  : 下午 2:43 2019/8/13 0013 
     * @params: 
     * @Desc  : 科室获取
     */
    MedicalRank findByOfficeId(Long id);
}

