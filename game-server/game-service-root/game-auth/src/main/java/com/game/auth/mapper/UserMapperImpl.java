package com.game.auth.mapper;

import com.game.common.dto.user.UserDto;
import com.game.common.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:26 2019/7/9 0009
 * @explain :
 */
@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserDto dto) {
        return null;
    }

    @Override
    public UserDto toDto(User entity) {
        return null;
    }

    @Override
    public List<User> toEntity(List<UserDto> dtoList) {
        return null;
    }

    @Override
    public List<UserDto> toDto(List<User> entityList) {
        return null;
    }
}
