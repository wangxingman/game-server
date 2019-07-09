package com.game.auth.mapper;

import com.game.common.dto.user.RoleDto;
import com.game.common.entity.user.Role;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:26 2019/7/9 0009
 * @explain :
 */
@Component
public class RoleMapperImpl implements RoleMapper {
    @Override
    public Role toEntity(RoleDto dto) {
        return null;
    }

    @Override
    public RoleDto toDto(Role entity) {
        return null;
    }

    @Override
    public List<Role> toEntity(List<RoleDto> dtoList) {
        return null;
    }

    @Override
    public List<RoleDto> toDto(List<Role> entityList) {
        return null;
    }
}
