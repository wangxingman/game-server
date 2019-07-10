package com.game.auth.api;

import com.game.auth.service.UserService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.entity.user.User;
import com.game.core.exception.EntityNotFoundException;
import com.game.core.utils.jpa.UserQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @Author : wx
 * @Desc :   crud 模糊分页查询
 * @Date :  上午 10:25 2019/6/20 0020
 * @explain :  用户的api
 */
@RestController
@RequestMapping("/user")
public class UserApi extends BaseApi {

    @Autowired
    private UserService userService;

    /**
     * @Author: wx
     * @Date : 上午 10:25 2019/6/20 0020
     * @params:
     * @Desc :  快速登录
     */
    @PostMapping("/fastLogin")
    public Result fastLogin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String passWord) {
        User user = userService.fastLogin(username, passWord);
        if (Objects.isNull(user)) {
            throw new EntityNotFoundException(User.class, "username", username, "passWord", passWord);
        }
        return success("返回的用户信息", user);
    }
    //todo 判断用户的权限

    /**
     * @Author: wx
     * @Date : 下午 2:36 2019/6/28 0028
     * @params:
     * @Desc :  新增用户 【初始化密码】
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        User currentUser = userService.addUser(user);
        return success(" 添加用户", currentUser);
    }

    /**
     * @Author: wx
     * @Date : 下午 3:55 2019/6/28 0028
     * @params:
     * @Desc :  修改用户
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return success();
    }

    /**
     * @Author: wx
     * @Date : 下午 4:47 2019/6/29 0029
     * @params: criteria 条件  pageable分页
     * @Desc : page=0&size=1&sort=id,desc
     */
    @PostMapping("/findByAllSearch")
    public Result findByAllSearch(UserQueryCriteria criteria, Pageable pageable) {
        return success("条件分页查询", userService.findByAllSearch(criteria, pageable));
    }

    /**
     * @Author: wx
     * @Date : 下午 5:57 2019/7/9 0009
     * @params:
     * @Desc :  查询全部
     */
    @PostMapping("/findByAll")
    public Result findByAll() {
        return success("这是全部的数据", userService.findByAll());
    }

    /**
     * @Author: wx
     * @Date : 下午 6:02 2019/7/9 0009
     * @params:
     * @Desc :  删除
     */
    @GetMapping("/deleteByUser")
    public Result deleteByUser(@RequestParam(name = "id") Long id) {
        userService.deleteByUser(id);
        return success("删除用户成功");
    }

    /**
     * @Author: wx
     * @Date : 下午 3:38 2019/7/10 0010
     * @params:
     * @Desc : 修改密码 【密码不应该显示的】
     */
    @PostMapping("/updateByPass")
    public Result updateByPass(@RequestBody User user, @RequestParam String newPass) {
        userService.updateByPass(user, newPass);
        return success("修改密码");
    }
}
