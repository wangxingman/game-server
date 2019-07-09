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
        return null;
    }

    @Override
    public DictDto toDto(Dict entity) {
        return null;
    }

    @Override
    public List<Dict> toEntity(List<DictDto> dtoList) {
        return null;
    }

    @Override
    public List<DictDto> toDto(List<Dict> entityList) {
        return null;
    }
}
