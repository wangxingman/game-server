package com.game.see.service.es;

import com.game.see.model.Customer;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:43 2019/8/2 0002
 * @explain :
 */
public interface CustomerService {

    /**
     * @Author: wx
     * @Date : 下午 6:04 2019/8/5 0005
     * @params:
     * @Desc :添加
     */
    Customer saveByCustomer(Customer customer);

    /**
     * @Author: wx
     * @Date : 下午 6:04 2019/8/5 0005
     * @params:
     * @Desc :删除
     */
    void deleteByCustomer(Customer customer);

    /**
     * @Author: wx
     * @Date : 下午 6:05 2019/8/5 0005
     * @params: customer
     * @Desc :修改
     */
    Customer updateByCustomer(Customer customer);

    /**
     * @Author: wx
     * @Date : 下午 6:05 2019/8/5 0005
     * @params:
     * @Desc :查询
     */
    Object findByCustomer();

    /**
     * @Author: wx
     * @Date : 上午 11:55 2019/8/6 0006
     * @params:
     * @Desc :  指定名字查询
     */
    Object findByCustomerName(String... searchParamsMap);

    /**
     * @Author: wx
     * @Date : 下午 4:49 2019/8/6 0006
     * @params:
     * @Desc : 原生操作
     */
    void RawByCustomer();

}
