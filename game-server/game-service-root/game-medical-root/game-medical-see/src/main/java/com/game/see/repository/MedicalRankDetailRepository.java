package com.game.see.repository;

import com.game.see.entity.MedicalRankDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:43 2019/8/8 0008
 * @explain :
 */
@Repository
public interface MedicalRankDetailRepository extends JpaRepository<MedicalRankDetail,Long>, JpaSpecificationExecutor {

    /**
     * @Author: wx
     * @Date  : 下午 4:44 2019/8/8 0008 
     * @params: 
     * @Desc  : 查找排班科室下面对应的医生
     */
    Set<MedicalRankDetail> findByRankOrderNumber(String rankOrderNumber);

    /**
     * @Author: wx
     * @Date  : 上午 11:05 2019/8/9 0009 
     * @params: 
     * @Desc  :  插叙这个 编号的以后几个人
     */
    Integer countByRankOrderNumber(String rankOrderNumber);
}
