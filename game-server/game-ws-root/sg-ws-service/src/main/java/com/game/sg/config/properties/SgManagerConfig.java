package com.game.sg.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 1:07 2019/5/17 0017
 * @explain : 初始化数据
 */
@ConfigurationProperties(prefix = "tx-lcn.manager")
@Component
@Data
public class SgManagerConfig {

    public static final int PORT_CHANGE_VALUE = 100;

    @Autowired
    public SgManagerConfig(ServerProperties serverProperties) {
        this.port = Objects.requireNonNull(serverProperties.getPort(), "TM http port not configured?") +
                PORT_CHANGE_VALUE;
    }

    /**
     * manager host
     */
    private String host = "127.0.0.1";

    /**
     * support  port
     */
    private int port;

    /**
     * netty heart check time (ms)
     */
    private long heartTime = 5 * 60 * 1000;

    /**
     * 事务处理并发等级
     */
    private int concurrentLevel;

    /**
     * 分布式事务锁超时时间
     */
    private long dtxLockTime = -1;

    /**
     * 分布式事务超时时间(ms)
     */
    private long dtxTime = 8 * 1000;

    /**
     * 后台密码
     */
    private String adminKey = "codingapi";

    /**
     * 是否允许异常回调
     */
    private boolean exUrlEnabled = true;

    /**
     * 异常回调地址
     */
    private String exUrl = "/provider/email-to/ujued@qq.com";

    /**
     * ID序列长度
     */
    private int seqLen = 12;

    private long machineId;

    private void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public void applyMachineId(long machineId) {
        setMachineId(machineId);
    }

    public long getDtxLockTime() {
        return dtxLockTime == -1 ? dtxTime : dtxLockTime;
    }
}
