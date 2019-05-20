package com.game.hall.state;

import com.game.core.thread.AbstractRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:41 2019/5/20 0020
 * @explain : 开始游戏
 */
@Slf4j
public class StartRunnable extends AbstractRunnable {

    private int TIME_LIMIT_SECONDS = 2500;
    private int TIMEOUT_SECONDS = 500;
    private int time;

    public StartRunnable(String str) {
        super.str = str;
        this.startTimeoutThread(TIME_LIMIT_SECONDS);
    }
    
    @Override
    public void run() {
        if(Objects.isNull(str)) {
           log.info("传输的值为null;");
        }
        
       if(time >= TIME_LIMIT_SECONDS) {

       }  else {
           //重新执行线程
           time += 500;
           this.startTimeoutMilliThread(TIMEOUT_SECONDS);
           return;
       }
    }
    
}
