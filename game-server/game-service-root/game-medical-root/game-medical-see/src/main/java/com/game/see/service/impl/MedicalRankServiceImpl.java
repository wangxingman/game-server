package com.game.see.service.impl;

import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityNotFoundException;
import com.game.see.entity.MedicalDoctor;
import com.game.see.entity.MedicalRank;
import com.game.see.repository.MedicalDoctorRepository;
import com.game.see.repository.MedicalRankRepository;
import com.game.see.service.MedicalRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:44 2019/7/25 0025
 * @explain :
 */
@Service
public class MedicalRankServiceImpl implements MedicalRankService {

    @Autowired
    private MedicalRankRepository medicalRankRepository;

    @Autowired
    private MedicalDoctorRepository medicalDoctorRepository;

    @Override
    public MedicalRank addByMedicalRank(MedicalRank medicalRank) {
        //选择科室 选择医生 排班在某一天 医生是不是隶属这个科室
        Long id = medicalRank.getMedicalDoctor().getId();
        Optional<MedicalDoctor> repository = medicalDoctorRepository.findById(id);
        MedicalDoctor medicalDoctor = repository.get();
        if (Objects.isNull(medicalDoctor)) {
            throw new BadRequestException("该医生不能排入该科室！");
        }
        //todo  排班到某一天的 时间 什么时间可预约
        @NotBlank Timestamp schedulingTime = medicalRank.getSchedulingTime();
        System.out.println("这是排班某一天的时间:" + schedulingTime);
        medicalRank.setOrderNumber(System.currentTimeMillis());
        medicalRank.setCreateTime(new Timestamp(System.currentTimeMillis()));
        medicalRank.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        return medicalRankRepository.save(medicalRank);
    }

    @Override
    public MedicalRank findByOne(Long id) {
        MedicalRank medicalRank = medicalRankRepository.findById(id).get();
        if (Objects.isNull(medicalRank)) {
            throw new EntityNotFoundException(MedicalDoctor.class, "id", id);
        }
        return medicalRank;
    }

    @Override
    public MedicalRank updateByMedicalRank(MedicalRank medicalRank) {
        return null;
    }
}
