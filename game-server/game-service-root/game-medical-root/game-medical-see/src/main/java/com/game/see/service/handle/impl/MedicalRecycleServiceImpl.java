package com.game.see.service.handle.impl;

import com.game.see.entity.handle.MedicalRecycle;
import com.game.see.repository.handle.MedicalRecycleRepository;
import com.game.see.service.handle.MedicalRecycleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 6:03 2019/8/13 0013
 * @explain :
 */
public class MedicalRecycleServiceImpl implements MedicalRecycleService {

    @Autowired
    private MedicalRecycleRepository medicalRecycleRepository;
    
    @Override
    public MedicalRecycle addMedicalRecycleService(MedicalRecycle medicalRecycle) {
        //物品 回收人
        return null;
    }
}
