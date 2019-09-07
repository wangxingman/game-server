package com.game.see.service.impl;

import com.game.core.exception.EntityNotFoundException;
import com.game.see.entity.MedicalSubscribe;
import com.game.see.repository.MedicalSubscribeRepository;
import com.game.see.repository.MedicalUserRepository;
import com.game.see.service.MedicalSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:18 2019/7/26 0026
 * @explain :
 */
@Service
public class MedicalSubscribeServiceImpl implements MedicalSubscribeService {

    @Autowired
    private MedicalSubscribeRepository medicalSubscribeRepository;

    @Autowired
    private MedicalUserRepository medicalUserRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MedicalSubscribe addMedicalSubscribe(MedicalSubscribe medicalSubscribe) {

        return null;
    }

    @Override
    public MedicalSubscribe findByOne(Long id) {
        Optional<MedicalSubscribe> repository = medicalSubscribeRepository.findById(id);
        MedicalSubscribe medicalSubscribe = repository.get();
        if (Objects.isNull(medicalSubscribe)) {
            throw new EntityNotFoundException(MedicalSubscribe.class, "id", id);
        }
        return medicalSubscribe;
    }

    @Override
    public MedicalSubscribe updateByMedicalSubscribe(MedicalSubscribe medicalSubscribe) {
        MedicalSubscribe medicalSubscribeFind = medicalSubscribeRepository.findByUserIdAndOfficeId(medicalSubscribe.getUserId(), medicalSubscribe.getOfficeId());
        if(Objects.isNull(medicalSubscribeFind)) {
            throw new EntityNotFoundException(MedicalSubscribe.class,"uid_officeId", medicalSubscribe.getOfficeId(),medicalSubscribe.getUserId());
        }
        MedicalSubscribe medicalSubscribeSave = medicalSubscribeRepository.save(medicalSubscribe);
        //todo 对于药品 器械的数量的减少
        return medicalSubscribeSave;
    }


}
