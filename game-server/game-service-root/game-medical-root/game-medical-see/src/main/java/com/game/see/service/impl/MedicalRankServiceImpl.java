package com.game.see.service.impl;

import com.game.common.constant.Const;
import com.game.common.entity.user.User;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityNotFoundException;
import com.game.core.utils.GeneratorNoUtil;
import com.game.core.utils.jpa.JpaPageUtil;
import com.game.core.utils.jpa.QueryHelp;
import com.game.core.utils.jpa.criteria.midical.RankQueryCriteria;
import com.game.see.entity.MedicalDoctor;
import com.game.see.entity.MedicalRank;
import com.game.see.entity.MedicalRankDetail;
import com.game.see.repository.MedicalDoctorRepository;
import com.game.see.repository.MedicalRankDetailRepository;
import com.game.see.repository.MedicalRankRepository;
import com.game.see.service.MedicalRankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

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
    private MedicalRankDetailRepository medicalRankDetailRepository;

    @Autowired
    private MedicalDoctorRepository medicalDoctorRepository;

    @Override
    public MedicalRank addByMedicalRank(MedicalRank medicalRank) {
        //todo  排班到某一天的 时间 什么时间可预约
        @NotBlank Timestamp schedulingTime = medicalRank.getSchedulingTime();
        System.out.println("这是排班某一天的时间:" + schedulingTime);
        medicalRank.setOrderNumber(GeneratorNoUtil.GeneratorNo(Const.Prefix.MEDICAL_RANK));
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
    public Object findByAllSearch(RankQueryCriteria criteria, Pageable pageable) {
        Page<User> page = medicalRankRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return JpaPageUtil.toPage(page);
    }

    @Override
    public Object findByTimeOffSearch(String OfficeName, Timestamp timestamp, Pageable pageable) {
        //todo 时间一个星期
        MedicalRank medicalRank = medicalRankRepository.findBySchedulingTimeAndOfficeName(timestamp, OfficeName);
        if (Objects.isNull(medicalRank)) {
            throw new EntityNotFoundException(MedicalRank.class, "OfficeName+timestamp", timestamp, OfficeName);
        }
        @NotNull String orderNumber = medicalRank.getOrderNumber();
        Set<MedicalRankDetail> medicalRankDetails = medicalRankDetailRepository.findByRankOrderNumber(orderNumber);
        if (Objects.isNull(medicalRankDetails)) {
            throw new EntityNotFoundException(MedicalRankDetail.class, "orderNumber",orderNumber);
        }
        return medicalRankDetails;
    }

    @Override
    public MedicalRank updateByMedicalRank(MedicalRank medicalRank) {
        return null;
    }
}
