package com.game.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:23 2019/5/13 0013
 * @explain :
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_info")
public class UserInfo implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "money_copy", nullable = true,columnDefinition="int(255) COMMENT 'money_copy'")
    private Integer money;
}
