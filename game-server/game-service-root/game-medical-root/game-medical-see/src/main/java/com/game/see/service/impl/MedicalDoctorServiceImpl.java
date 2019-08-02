package com.game.see.service.impl;

import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import com.game.see.entity.MedicalDoctor;
import com.game.see.entity.MedicalOffice;
import com.game.see.repository.MedicalDoctorRepository;
import com.game.see.service.MedicalDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:06 2019/7/26 0026
 * @explain :
 */
@Service
public class MedicalDoctorServiceImpl implements MedicalDoctorService {

    @Autowired
    private MedicalDoctorRepository medicalDoctorRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MedicalDoctor addByMedicalDoctor(MedicalDoctor medicalDoctor) {
        @NotNull Long dockerId
                = Optional.of(medicalDoctor.getDoctorId() != null ? System.currentTimeMillis() : System.currentTimeMillis()).orElse(System.currentTimeMillis());
        List<MedicalDoctor> medicalDoctors = medicalDoctorRepository.findAll();
        medicalDoctors.forEach(medicalDoctor1 -> {
            if (medicalDoctor1.getName().equals(medicalDoctor.getName()) || medicalDoctor1.getId().equals(medicalDoctor.getId())) {
                throw new EntityExistException(MedicalOffice.class, "medicalOffice", medicalDoctor.getName());
            } else if (dockerId.equals(medicalDoctor1.getDoctorId())) {
                throw new EntityExistException(MedicalOffice.class, "medicalOffice", dockerId);
            } else if(medicalDoctor1.getPhone().equals(medicalDoctor.getPhone())) {
                throw new EntityExistException(MedicalOffice.class, "medicalOffice", medicalDoctor.getPhone());
            }
        });
        medicalDoctor.setDoctorId(dockerId);
        return medicalDoctorRepository.save(medicalDoctor);
    }

    @Override
    public MedicalDoctor findByOne(Long id) {
        Optional<MedicalDoctor> repository = medicalDoctorRepository.findById(id);
        if (Objects.isNull(repository)) {
            throw new EntityNotFoundException(MedicalDoctor.class, "id", id);
        }
        return repository.get();
    }
}
