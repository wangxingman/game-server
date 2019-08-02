package com.game.see.api;

import com.game.common.comman.api.BaseApi;
import com.game.see.entity.MedicalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:32 2019/7/24 0024
 * @explain :  所有的诊所
 */
@RestController
@RequestMapping("/MedicalClinic")
public class MedicalClinicApi extends BaseApi {
    
    
}
