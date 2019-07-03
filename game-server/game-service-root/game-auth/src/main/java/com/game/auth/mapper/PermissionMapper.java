package com.game.auth.mapper;

import com.game.common.dto.user.PermissionDto;
import com.game.common.entity.user.Permission;
import com.game.common.mapper.inter.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:51 2019/7/1 0001
 * @explain :
 */
@Component
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper extends EntityMapper<PermissionDto, Permission> {
}
