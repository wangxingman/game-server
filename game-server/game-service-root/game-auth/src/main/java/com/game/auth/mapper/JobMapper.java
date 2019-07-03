package com.game.auth.mapper;

import com.game.common.dto.user.JobDto;
import com.game.common.entity.user.Job;
import com.game.common.mapper.inter.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date  : 下午 3:15 2019/6/28 0028
 * @params:
 * @Desc  :
 */
@Component
@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends EntityMapper<JobDto, Job> {

    @Mapping(source = "deptSuperiorName", target = "deptSuperiorName")
    JobDto toDto(Job job, String deptSuperiorName);
}