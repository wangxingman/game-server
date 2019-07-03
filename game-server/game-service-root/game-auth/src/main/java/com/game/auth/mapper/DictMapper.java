package com.game.auth.mapper;

import com.game.common.dto.user.DictDto;
import com.game.common.entity.user.Dict;
import com.game.common.mapper.inter.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:23 2019/7/2 0002
 * @explain :
 */
@Component
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends EntityMapper<DictDto, Dict> {
}
