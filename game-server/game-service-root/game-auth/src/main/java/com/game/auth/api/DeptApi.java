package com.game.auth.api;

import com.game.auth.service.DeptService;
import com.game.common.comman.api.BaseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:43 2019/7/2 0002
 * @explain :
 */
@RestController
@RequestMapping("dept")
public class DeptApi extends BaseApi {
    
    @Autowired
    private DeptService deptService;
}
