package com.game.see.service.impl;

import com.game.common.constant.Const;
import com.game.core.exception.BadRequestException;
import com.game.core.utils.GeneratorNoUtil;
import com.game.core.utils.RandomUtil;
import com.game.see.entity.MedicalRank;
import com.game.see.entity.MedicalRankDetail;
import com.game.see.entity.MedicalRankUpdate;
import com.game.see.repository.MedicalRankDetailRepository;
import com.game.see.repository.MedicalRankRepository;
import com.game.see.repository.MedicalRankUpdateRepository;
import com.game.see.service.MedicalRankDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:31 2019/8/8 0008
 * @explain :
 */
@Service
public class MedicalRankDetailServiceImpl implements MedicalRankDetailService {

    @Autowired
    private MedicalRankRepository medicalRankRepository;

    @Autowired
    private MedicalRankDetailRepository medicalRankDetailRepository;

    @Autowired
    private MedicalRankUpdateRepository medicalRankUpdateRepository;

    @Value("${duty.number}")
    private Integer number;


    /**
     * @Author: wx
     * @Date : 下午 5:37 2019/8/8 0008
     * @params:
     * @Desc :  首先已经开始预约了 不可以修改排班
     * 根据医院的患者流量 写一个每天安排医生的量
     * 医生 走了 必须找一个 医生，来替代 所以【我们 必须找一个东西去记录医生的 排班记录 】
     * 还有医生的状态 已经排班了 还是没有排班
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public MedicalRankDetail updateByMedicalRankDetail(MedicalRankDetail medicalRankDetail, Integer status) {
        // 0 换班 1休息
        if (status.equals(Const.number.ZERO)) {
            @NotNull String rankOrderNumber = medicalRankDetail.getRankOrderNumber();
            MedicalRank medicalRank = medicalRankRepository.findByOrderNumber(rankOrderNumber);
            Integer count = medicalRankDetailRepository.countByRankOrderNumber(medicalRankDetail.getRankOrderNumber());
            if (number > count && medicalRank!=null) {
                medicalRankUpdateRepository.save(MedicalRankUpdate.builder()
                        .medicalDoctorName(medicalRankDetail.getMedicalDoctor().getName())
                        .medicalDoctorId(medicalRankDetail.getMedicalDoctor().getId())
                        .createTime(new Timestamp(System.currentTimeMillis()))
                        .medicalRankNumber(medicalRank.getOrderNumber())
                        .orderNumber(GeneratorNoUtil.GeneratorNo(Const.Prefix.MEDICAL_RANK_UPDATE))
                        .status(status).build());
                return medicalRankDetailRepository.saveAndFlush(medicalRankDetail);
            }
        } else if (status.equals(Const.number.ONE)) {
            Integer count = medicalRankDetailRepository.countByRankOrderNumber(medicalRankDetail.getRankOrderNumber());
            if (count > number) {
                medicalRankDetailRepository.delete(medicalRankDetail);
            } else {
                throw new BadRequestException("当前患者比较多，只能上班了！");
            }
        } else {
            throw new BadRequestException("您传输的状态有误！");
        }
        return null;
    }
}
