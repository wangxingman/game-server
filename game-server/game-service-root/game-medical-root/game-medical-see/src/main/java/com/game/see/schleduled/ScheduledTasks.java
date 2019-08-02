package com.game.see.schleduled;

import com.game.common.constant.Const;
import com.game.see.entity.MedicalSubscribe;
import com.game.see.repository.MedicalSubscribeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:39 2019/7/29 0029
 * @explain :  定时任务 【定时推送 难道我这里 也需要 来一个长连接推送吗？】
 */
@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    private MedicalSubscribeRepository medicalSubscribeRepository;

    /**
     * @Author: wx
     * @Date : 下午 3:43 2019/7/29 0029
     * @params:
     * @Desc : 每天下午检查当天6点的时候
     */
    @Scheduled(cron = " 0 0 18 * * ? ")
    public Long scheduledMedicalSubscribe() {
        //todo 没有查询当天
        Set<MedicalSubscribe> medicalSubscribes = medicalSubscribeRepository.findByIfMoneyAndIfDiagnosis(Const.number.ONE, Const.number.ONE);
        if (Objects.nonNull(medicalSubscribes)) {
            log.info("这里查询多少人没问诊！");
            long count = medicalSubscribes.stream().count();
            return count;
        }
        return null;
    }


}
