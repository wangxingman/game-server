package com.game.auth.mapper;

import com.game.common.dto.user.DictDetailDto;
import com.game.common.entity.user.DictDetail;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:25 2019/7/9 0009
 * @explain :
 */
@Component
public class DictDetailMapperImpl implements DictDetailMapper {
    @Override
    public DictDetail toEntity(DictDetailDto dto) {
        return this.toEntity(dto);
    }

    @Override
    public DictDetailDto toDto(DictDetail entity) {
        return this.toDto(entity);
    }
    @Override
    public List<DictDetail> toEntity(List<DictDetailDto> dtoList) {
        return this.toEntity(dtoList);
    }

    @Override
    public List<DictDetailDto> toDto(List<DictDetail> entityList) {
        return this.toDto(entityList);
    }
}
