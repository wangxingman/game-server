package com.game.auth.mapper;

import com.game.common.dto.user.RoleDto;
import com.game.common.entity.user.Role;
import com.game.common.mapper.inter.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date  : 下午 3:42 2019/6/28 0028 
 * @params: 
 * @Desc  : 
 */
@Component
@Mapper(componentModel = "spring", uses = {DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
    /*PermissionMapper.class, MenuMapper.class, */
}
