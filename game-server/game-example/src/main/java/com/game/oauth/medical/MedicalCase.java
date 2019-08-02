package com.game.oauth.medical;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:51 2019/7/24 0024
 * @explain :  病例【就是之前患者所得的病】
 */
@Data
@Builder
@Entity
@Table(name="medical_case")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalCase implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = MedicalUser.Update.class)
    private Long id;

    /**
     *  流水的id
     */
    @Column(name = "bill_id",nullable = false)
    @NotBlank
    private String billId;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private MedicalUser medicalUser;

}
