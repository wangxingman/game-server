package com.game.example.batch;

import com.game.common.entity.user.User;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 12:04 2019/9/6 0006
 * @explain :  自定义效验器
 */
public class CsvItemProcessor extends ValidatingItemProcessor<User> {
    /**
     * @Author: wx
     * @Date : 下午 2:31 2019/9/6 0006
     * @params:
     * @Desc :  重写验证值是否有效
     */
    @Override
    public User process(User item) throws ValidationException {
        super.process(item);
        //这里面可以 进行的值得操作
        return item;
    }
}
