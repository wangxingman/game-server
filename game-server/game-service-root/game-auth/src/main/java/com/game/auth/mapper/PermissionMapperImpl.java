package com.game.auth.mapper;

import com.game.common.dto.user.PermissionDto;
import com.game.common.entity.user.Permission;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:26 2019/7/9 0009
 * @explain :
 */
@Component
public class PermissionMapperImpl implements  PermissionMapper {
    @Override
    public Permission toEntity(PermissionDto dto) {
        return this.toEntity(dto);
    }

    @Override
    public PermissionDto toDto(Permission entity) {
        return this.toDto(entity);
    }

    @Override
    public List<Permission> toEntity(List<PermissionDto> dtoList) {
        return this.toEntity(dtoList);
    }

    @Override
    public List<PermissionDto> toDto(List<Permission> entityList) {
        return this.toDto(entityList);
    }
}
