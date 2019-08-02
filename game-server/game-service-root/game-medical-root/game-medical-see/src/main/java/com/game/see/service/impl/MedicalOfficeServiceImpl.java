package com.game.see.service.impl;

import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import com.game.see.entity.MedicalOffice;
import com.game.see.repository.MedicalOfficeRepository;
import com.game.see.service.MedicalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:24 2019/7/26 0026
 * @explain :
 */
@Service
public class MedicalOfficeServiceImpl implements MedicalOfficeService {

    @Autowired
    private MedicalOfficeRepository medicalOfficeRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MedicalOffice addByMedicalOffice(MedicalOffice medicalOffice) {
        //添加科室 名字应该不能 使用一样的
        List<MedicalOffice> medicalOfficeList = medicalOfficeRepository.findAll();
        medicalOfficeList.forEach(medicalOffice1 -> {
            if (medicalOffice1.getName().equals(medicalOffice.getName()) && medicalOffice1.getId().equals(medicalOffice.getId())) {
                throw new EntityExistException(MedicalOffice.class, "medicalOffice", medicalOffice.getName());
            }
        });
        if (medicalOffice.getCreateTime() == null) {
            medicalOffice.setCreateTime(new Timestamp(System.currentTimeMillis()));
        } else if(medicalOffice.getUpdateTime() == null) {
            medicalOffice.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        }
        return medicalOfficeRepository.save(medicalOffice);
    }

    @Override
    public MedicalOffice selectByOne(Long id) {
        Optional<MedicalOffice> repository = medicalOfficeRepository.findById(id);
        if (Objects.isNull(repository)) {
            throw new EntityNotFoundException(MedicalOffice.class, "id", id);
        }
        return repository.get();
    }

}
