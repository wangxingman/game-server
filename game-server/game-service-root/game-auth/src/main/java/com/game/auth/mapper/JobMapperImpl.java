package com.game.auth.mapper;

import com.game.common.dto.user.JobDto;
import com.game.common.entity.user.Job;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:26 2019/7/9 0009
 * @explain :
 */
@Component
public class JobMapperImpl implements JobMapper {
    @Override
    public JobDto toDto(Job job, String deptSuperiorName) {
        return null;
    }

    @Override
    public Job toEntity(JobDto dto) {
        return this.toEntity(dto);
    }

    @Override
    public JobDto toDto(Job entity) {
        return this.toDto(entity);
    }

    @Override
    public List<Job> toEntity(List<JobDto> dtoList) {
        return this.toEntity(dtoList);
    }

    @Override
    public List<JobDto> toDto(List<Job> entityList) {
        return this.toDto(entityList);
    }
}
