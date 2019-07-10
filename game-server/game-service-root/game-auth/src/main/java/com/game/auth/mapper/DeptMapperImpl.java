package com.game.auth.mapper;

import com.game.common.dto.user.DeptDto;
import com.game.common.dto.user.DictDto;
import com.game.common.entity.user.Dept;
import com.game.common.entity.user.Dict;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:21 2019/7/9 0009
 * @explain :
 */
@Component
@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class DeptMapperImpl implements DeptMapper {
    @Override
    public Dept toEntity(DeptDto dto) {
        return this.toEntity(dto);
    }


    @Override
    public DeptDto toDto(Dept entity) {
        return this.toDto(entity);
    }

    @Override
    public List<Dept> toEntity(List<DeptDto> dtoList) {
        return this.toEntity(dtoList);
    }

    @Override
    public List<DeptDto> toDto(List<Dept> entityList) {
        return this.toDto(entityList);
    }
}
