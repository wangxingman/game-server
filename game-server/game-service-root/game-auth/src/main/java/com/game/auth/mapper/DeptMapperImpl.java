package com.game.auth.mapper;

import com.game.common.dto.user.DeptDto;
import com.game.common.dto.user.DictDto;
import com.game.common.entity.user.Dept;
import com.game.common.entity.user.Dict;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:21 2019/7/9 0009
 * @explain :
 */
@Component
public class DeptMapperImpl implements DeptMapper {
    @Override
    public Dept toEntity(DeptDto dto) {
        return null;
    }

    @Override
    public DeptDto toDto(Dept entity) {
        return null;
    }

    @Override
    public List<Dept> toEntity(List<DeptDto> dtoList) {
        return null;
    }

    @Override
    public List<DeptDto> toDto(List<Dept> entityList) {
        return null;
    }
}
