package com.game.see.service.impl;

import com.game.common.constant.Const;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import com.game.core.utils.GeneratorNoUtil;
import com.game.core.utils.RandomUtil;
import com.game.see.entity.*;
import com.game.see.repository.*;
import com.game.see.service.MedicalRankDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:31 2019/8/8 0008
 * @explain :
 */
@Service
public class MedicalRankDetailServiceImpl implements MedicalRankDetailService {

    @Autowired
    private MedicalUserRepository medicalUserRepository;

    @Autowired
    private MedicalOfficeRepository medicalOfficeRepository;

    @Autowired
    private MedicalDoctorRepository medicalDoctorRepository;

    @Autowired
    private MedicalRankRepository medicalRankRepository;

    @Autowired
    private MedicalSubscribeRepository medicalSubscribeRepository;

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
            if (number > count && medicalRank != null) {
                medicalRankUpdateRepository.save(MedicalRankUpdate.builder()
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MedicalRankDetail addByMedicalRankDetail(MedicalRankDetail medicalRankDetail, Long officeId) {
        @NotNull String rankOrderNumber = medicalRankDetail.getRankOrderNumber();
        MedicalRank medicalRank = medicalRankRepository.findByOrderNumber(rankOrderNumber);
        if (Objects.isNull(medicalRank)) {
            throw new EntityNotFoundException(MedicalRank.class, "rankOrderNumber", rankOrderNumber);
        }
        MedicalRank repository = medicalRankRepository.findByOfficeId(officeId);
        if (Objects.isNull(repository)) {
            throw new EntityNotFoundException(MedicalRank.class, "排班没有该科室的排班", officeId);
        }
        MedicalRankDetail medicalRankDetail1 = medicalRankDetailRepository.findById(medicalRankDetail.getId()).get();
        if (Objects.nonNull(medicalRankDetail1)) {
            throw new EntityExistException(MedicalRankDetail.class, "officeId", medicalRankDetail.getId());
        }
        MedicalDoctor medicalDoctor = medicalDoctorRepository.findById(medicalRankDetail.getDoctorId()).get();
        if (!medicalDoctor.getMedicalOffice().getId().equals(officeId)) {
            throw new EntityNotFoundException(MedicalDoctor.class, "医生不在该科室", medicalDoctor.getId());
        }
        MedicalRankDetail medicalRankDetailOne = MedicalRankDetail.builder()
                .allNumber(medicalRankDetail.getAllNumber())
                .finishNumber(Const.number.ZERO)
                .doctorId(medicalRankDetail.getDoctorId())
                .doctorName(medicalRankDetail.getDoctorName())
                .money(medicalRankDetail.getMoney())
                .rankOrderNumber(medicalRank.getOrderNumber())
                .build();
        MedicalRankDetail medicalRankDetailSave = medicalRankDetailRepository.save(medicalRankDetailOne);
        return medicalRankDetailSave;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delByMedicalRankDetail(Long id) {
        medicalRankDetailRepository.deleteById(id);
    }

    @Override
    public List<MedicalRankDetail> findAllByMedicalRankDetail(MedicalRankDetail medicalRankDetail) {
        return null;
    }

    @Override
    public void addSubscribeRank(String uid, Long officeId, Long id) {
        MedicalSubscribe medicalSubscribeFind = medicalSubscribeRepository.findByUserIdAndOfficeId(uid, officeId);
        if (Objects.nonNull(medicalSubscribeFind)) {
            throw new EntityExistException(MedicalSubscribe.class, "officeId----uid", officeId, uid);
        }
        MedicalUser medicalUser = medicalUserRepository.findByUserId(uid);
        if (Objects.isNull(medicalUser)) {
            throw new EntityNotFoundException(MedicalUser.class, "uid", uid);
        }
        MedicalSubscribe medicalSubscribe = MedicalSubscribe.builder()
                .userId(uid)
                .userName(medicalUser.getName())
                .officeId(officeId)
                .ifDiagnosis(Const.number.ZERO)
                .ifMoney(Const.number.ZERO)
                .subscribeId(GeneratorNoUtil.GeneratorNo(Const.Prefix.MEDICAL_SUBSCRIBE))
                .build();
        MedicalSubscribe medicalSubscribeSave = medicalSubscribeRepository.save(medicalSubscribe);
        MedicalRankDetail medicalRankDetaiFind = medicalRankDetailRepository.findById(id).get();
        if (Objects.isNull(medicalRankDetaiFind)) {
            throw new EntityNotFoundException(MedicalRankDetail.class, "排班的id", id);
        }
        List<MedicalSubscribe> medicalSubscribes = medicalRankDetaiFind.getMedicalSubscribes();
        medicalSubscribes.add(medicalSubscribeSave);
        medicalRankDetaiFind.setMedicalSubscribes(medicalSubscribes);
        medicalRankDetailRepository.saveAndFlush(medicalRankDetaiFind);
    }

    @Override
    public void delSubscribeRank(String uid, Long officeId, Long id) {
        MedicalRankDetail medicalRankDetaiFind = medicalRankDetailRepository.findById(id).get();
        if (Objects.isNull(medicalRankDetaiFind)) {
            throw new EntityNotFoundException(MedicalRankDetail.class, "排班的id", id);
        }
        MedicalSubscribe medicalSubscribeFind = medicalSubscribeRepository.findByUserIdAndOfficeId(uid, officeId);
        if (Objects.isNull(medicalSubscribeFind)) {
            throw new EntityNotFoundException(MedicalSubscribe.class, "officeId----uid", officeId, uid);
        }
        List<MedicalSubscribe> medicalSubscribes = medicalRankDetaiFind.getMedicalSubscribes();
        boolean contains = medicalSubscribes.contains(medicalSubscribeFind);
        if (!contains) {
            throw new EntityNotFoundException(MedicalRankDetail.class, "不存在", contains);
        } else {
            medicalSubscribes.remove(medicalSubscribeFind);
            medicalRankDetaiFind.setMedicalSubscribes(medicalSubscribes);
            medicalRankDetailRepository.saveAndFlush(medicalRankDetaiFind);
        }
    }
}
