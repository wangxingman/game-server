package com.game.auth.mapper;

import com.game.common.dto.user.UserDto;
import com.game.common.entity.user.User;
import com.game.common.mapper.inter.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:12 2019/6/28 0028
 * @explain :
 */
public interface UserMapper extends EntityMapper<UserDto, User> {
}
