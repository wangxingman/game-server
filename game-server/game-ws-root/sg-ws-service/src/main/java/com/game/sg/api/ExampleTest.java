package com.game.sg.api;

import com.game.common.dto.NettyParams;
import com.game.core.thread.AbstractRunnable;
import com.game.sg.state.ExampleRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Handler;

/**
 * @Auther: wx
 * @Desc:
 * @Date: 下午 4:51 2019/5/5 0005
 */

@Slf4j
@RestController
public class ExampleTest {

    public static ExampleRunnable getExampleRunnable() {
         return new ExampleRunnable();
    }
    
    @Autowired
    private NettyParams nettyParams;

    /**
     * @Author: wx
     * @Desc:   测试获取对应的端口
     * @Date: 下午 4:54 2019/5/5 0005
     * @params:
     */
    @GetMapping(value ="/getNettyParams" )
    public Integer getNettyParams() {
        Map<Integer, String> localMap = nettyParams.getLocalMap();

        log.info("获取yml文件下面的端口:"+nettyParams.getPort());
        return  nettyParams.getPort();
    }

    public static void main(String[] args) {
        getExampleRunnable().startThread();
    }
}
