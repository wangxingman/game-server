package com.game.see.service.impl;

import com.game.core.exception.EntityNotFoundException;
import com.game.see.entity.MedicalUser;
import com.game.see.repository.MedicalUserRepository;
import com.game.see.service.MedicalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:40 2019/7/25 0025
 * @explain :
 */
@Service
public class MedicalUserServiceImpl implements MedicalUserService {

    @Autowired
    private MedicalUserRepository medicalUserRepository;

    @Override
    public MedicalUser getByMedicalUser(Long id) {
        MedicalUser user = medicalUserRepository.findById(id).get();
        if (Objects.isNull(user)) {
            throw new EntityNotFoundException(MedicalUser.class, "id", id);
        }
        user.getMedicalMedicines().stream().forEachOrdered(medicalMedicine -> {
            if (medicalMedicine.getAllNumber() != null) {
                medicalMedicine.setAllNumber(0);
            }
        });
        return user;
    }

    @Override
    public MedicalUser addByMedicalUser(MedicalUser medicalUser) {

        return null;
    }

    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
       if (!b) {
            System.out.println("---!b----");
        }
    }

}
