package com.game.auth.mapper;

import com.game.common.dto.user.DeptDto;
import com.game.common.entity.user.Dept;
import com.game.common.mapper.inter.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @Author: wx
 * @Date  : 下午 3:15 2019/6/28 0028 
 * @params: 
 * @Desc  :
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends EntityMapper<DeptDto, Dept> {

}