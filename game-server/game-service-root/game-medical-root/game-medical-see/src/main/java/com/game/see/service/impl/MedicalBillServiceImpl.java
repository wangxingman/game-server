package com.game.see.service.impl;

import com.game.see.entity.MedicalBill;
import com.game.see.repository.MedicalBillRepository;
import com.game.see.service.MedicalBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:45 2019/7/26 0026
 * @explain :
 */
@Service
public class MedicalBillServiceImpl implements MedicalBillService {

    @Autowired
    private MedicalBillRepository medicalBillRepository;

    @Override
    public MedicalBill addMedicalBill(MedicalBill medicalBill) {
        return null;
    }

    @Override
    public MedicalBill findByOne(Long id) {
        return null;
    }
}
