package com.game.see.service;


import com.game.core.utils.jpa.criteria.midical.RankQueryCriteria;
import com.game.see.entity.MedicalRank;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:39 2019/7/25 0025
 * @explain :
 */
public interface MedicalRankService {

    /**
     * @Author: wx
     * @Date  : 上午 9:41 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalRank addByMedicalRank(MedicalRank medicalRank);

    /**
     * @Author: wx
     * @Date  : 上午 9:41 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalRank updateByMedicalRank(MedicalRank medicalRank);
    
    /**
     * @Author: wx
     * @Date  : 上午 11:53 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalRank findByOne(Long id);
    
    /**
     * @Author: wx
     * @Date  : 下午 3:37 2019/8/8 0008 
     * @params: 
     * @Desc  :
     */
    Object findByAllSearch(RankQueryCriteria criteria, Pageable pageable);
    
    /**
     * @Author: wx
     * @Date  : 下午 4:20 2019/8/8 0008 
     * @params: 
     * @Desc  :
     */
    Object findByTimeOffSearch(String OfficeName, Timestamp timestamp, Pageable pageable);
}
