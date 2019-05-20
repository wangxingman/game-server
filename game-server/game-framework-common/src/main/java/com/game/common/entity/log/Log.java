package com.game.common.entity.log;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 11:29 2019/5/20 0020
 * @explain :
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tId;

    /**
     * 操作人
     */
    private String tUserName;

    /**
     * 操作人id
     */
    private Integer tUserId;

    /**
     * 操作类型
     */
    private String tType;

    /**
     * 成功：失败
     */
    private Integer tOrOperation;

    /**
     * 操作明细
     */
    private String tOperationDetail;

    /**
     * 创建时间
     */
    private Date tCreateTime;
}
