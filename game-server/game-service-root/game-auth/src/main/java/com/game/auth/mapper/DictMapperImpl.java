package com.game.auth.mapper;

import com.game.common.dto.user.DictDto;
import com.game.common.entity.user.Dict;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:25 2019/7/9 0009
 * @explain :
 */
@Component
public class DictMapperImpl implements DictMapper {
    @Override
    public Dict toEntity(DictDto dto) {
        return this.toEntity(dto);
    }

    @Override
    public DictDto toDto(Dict entity) {
        return this.toDto(entity);
    }

    @Override
    public List<Dict> toEntity(List<DictDto> dtoList) {
        return this.toEntity(dtoList);
    }

    @Override
    public List<DictDto> toDto(List<Dict> entityList) {
        return this.toDto(entityList);
    }
}
