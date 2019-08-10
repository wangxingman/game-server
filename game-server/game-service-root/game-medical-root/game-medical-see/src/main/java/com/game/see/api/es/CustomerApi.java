package com.game.see.api.es;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.see.model.Customer;
import com.game.see.service.es.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:46 2019/8/2 0002
 * @explain : es的操作
 */
@RestController
@RequestMapping("/customer")
public class CustomerApi extends BaseApi {

    @Autowired
    private CustomerService customerService;

    /**
     * @Author: wx
     * @Date : 下午 7:59 2019/8/5 0005
     * @params:
     * @Desc :  添加数据
     */
    @RequestMapping("/saveByCustomer")
    public Result saveByCustomer(@RequestBody Customer customer) {
        Customer byCustomer = customerService.saveByCustomer(customer);
        return success("添加数据", byCustomer);
    }

    /**
     * @Author: wx
     * @Date : 下午 7:59 2019/8/5 0005
     * @params:
     * @Desc : 查询数据
     */
    @RequestMapping("/findByCustomer")
    public Result findByCustomer() {
        return success("查询数据", customerService.findByCustomer());
    }

    /**
     * @Author: wx
     * @Date : 上午 11:50 2019/8/6 0006
     * @params:
     * @Desc : 指定名字查询
     */
    @GetMapping("/findByCustomerName")
    public Result findByCustomerName(@RequestParam String field) {
        return success("指定名字查询", customerService.findByCustomerName(field));
    }

}
