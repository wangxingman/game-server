package com.game.auth.mapper;

import com.game.common.dto.user.DictDetailDto;
import com.game.common.entity.user.DictDetail;
import com.game.common.mapper.inter.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date  : 下午 2:44 2019/7/2 0002 
 * @params: 
 * @Desc  :
 */
@Component
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends EntityMapper<DictDetailDto, DictDetail> {

}