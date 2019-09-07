package com.game.see.repository;

import com.game.see.entity.MedicalSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:20 2019/7/26 0026
 * @explain :
 */
@Repository
public interface MedicalSubscribeRepository  extends JpaRepository<MedicalSubscribe,Long>, JpaSpecificationExecutor {
    
    /**
     * @Author: wx
     * @Date  : 下午 3:49 2019/7/29 0029 
     * @params: 
     * @Desc  : 查询已经支付但是没有看诊的患者
     */
     Set<MedicalSubscribe> findByIfMoneyAndIfDiagnosis(Integer ifMoney,Integer ifDiagnosis);
     
     /**
      * @Author: wx
      * @Date  : 下午 5:56 2019/8/12 0012 
      * @params: 
      * @Desc  : 查询 患者不应多个病单
      */
     MedicalSubscribe findByUserIdAndOfficeId(String userId,Long officeId);
     
}
