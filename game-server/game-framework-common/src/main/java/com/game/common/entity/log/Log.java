package com.game.common.entity.log;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 11:29 2019/5/20 0020
 * @explain :
 */
@Entity(name = "g_log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer tId;

    /**
     * 操作人
     */
    @Column(name = "t_name", nullable = true,columnDefinition="varchar(255) COMMENT '操作人'")
    private String tUserName;

    /**
     * 操作人id
     */
    @Column(name = "t_user_id", nullable = true,columnDefinition="int(11) COMMENT '操作人id'")
    private Integer tUserId;

    /**
     * 操作类型
     */
    @Column(name = "t_type", nullable = true,columnDefinition="varchar(255) COMMENT '操作类型'")
    private String tType;

    /**
     * 成功：失败
     */
    @Column(name = "t_or_operation", nullable = true,columnDefinition="int(11) COMMENT '成功：失败'")
    private Integer tOrOperation;

    /**
     * 操作明细
     */
    @Column(name = "t_operation_detail", nullable = true,columnDefinition="varchar(255) COMMENT '操作明细'")
    private String tOperationDetail;

    /**
     * 创建时间
     */
    @JSONField(serialize = false)
    @Column(name = "createtime", nullable = true)
    private Date createtime;

}
