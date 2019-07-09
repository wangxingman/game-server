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
        return null;
    }

    @Override
    public JobDto toDto(Job entity) {
        return null;
    }

    @Override
    public List<Job> toEntity(List<JobDto> dtoList) {
        return null;
    }

    @Override
    public List<JobDto> toDto(List<Job> entityList) {
        return null;
    }
}
