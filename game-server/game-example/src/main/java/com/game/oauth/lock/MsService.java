package com.game.oauth.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:05 2019/8/7 0007
 * @explain :
 */
@Service
public class MsService {

    @Autowired
    private RedisLock redisLock;

    /**
     * 商品详情
     *
     */
    private static HashMap<String, Integer> product = new HashMap();
    /**
     * 订单表
     *
     */
    private static HashMap<String, String> orders = new HashMap();
    /**
     * 库存表
     *
     */
    private static HashMap<String, Integer> stock = new HashMap();

    static {
        product.put("123", 10000);
        stock.put("123", 10000);
    }

    public String select_info(String product_id) {
        return "限量抢购商品XXX共" + product.get(product_id) + ",现在成功下单" + orders.size()
                + ",剩余库存" + stock.get(product_id) + "件";
    }
    /**
     * 高并发没问题，效率还行
     *
     * 同一个东西 别人 在操作的时候 是添加不进去数据的【也就是锁存在的时候】
     *
     * 如果是操作 这个东西 我直接锁行 不就行了 吗
     * 
     * @param product_id
     * @return
     */
    public String order3(String product_id) throws Exception {
        /**
         * redis加锁
         */
        String value = System.currentTimeMillis() + 10000 + "";
        //没加入进去 添加锁
        if (!redisLock.lockThree(product_id, value)) {
            throw new Exception();
        }
        if (stock.get(product_id) == 0) {
            return "活动已经结束了";
            //已近买完了
        } else {
            //还没有卖完
            try {
                //模拟操作数据库
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            orders.put(MyStringUtils.getuuid(), product_id);
            stock.put(product_id, stock.get(product_id) - 1);
        }
        //数据库加入完成 我们解锁
        redisLock.unlock(product_id, value);
        return select_info(product_id);
    }

}
