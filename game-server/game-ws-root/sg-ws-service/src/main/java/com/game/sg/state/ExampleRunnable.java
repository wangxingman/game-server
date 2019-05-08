package com.game.sg.state;

import com.game.core.thread.AbstractRunnable;
import lombok.Data;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 8:30 2019/5/7 0007
 */
@Data
public class ExampleRunnable extends AbstractRunnable {

    @Override
    public void run() {
        System.out.println("执行一个线程！");
    }
}
