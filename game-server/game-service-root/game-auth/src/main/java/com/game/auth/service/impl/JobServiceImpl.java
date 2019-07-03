package com.game.auth.service.impl;

import com.game.auth.mapper.JobMapper;
import com.game.auth.repository.JobRepository;
import com.game.auth.service.JobService;
import com.game.common.dto.user.JobDto;
import com.game.common.entity.user.Job;
import com.game.common.entity.user.Role;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:53 2019/7/2 0002
 * @explain :
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Object findByAll() {
        List<Job> jobList = jobRepository.findAll();
        if(Objects.isNull(jobList)) {
            throw new BadRequestException("jobList为null");
        }
        return jobList;
    }

    @Override
    public JobDto findById(Long id) {
        Job job = jobRepository.getOne(id);
        if (Objects.isNull(job)) {
            throw new EntityNotFoundException(Job.class, "id", id);
        }
        return jobMapper.toDto(job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JobDto addByJob(Job job) {
        Job currentJob = jobRepository.findByName(job.getName());
        if (Objects.nonNull(currentJob)) {
            throw new EntityExistException(Job.class, "name", job.getName());
        }
        JobDto jobDto = jobMapper.toDto(jobRepository.save(job));
        return jobDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JobDto updateByJob(Job job) {
        Job currentJob = jobRepository.getOne(job.getId());
        if (Objects.isNull(currentJob)) {
           throw  new EntityNotFoundException(Job.class, "id", job.getId());
        }
        currentJob.setEnabled(job.getEnabled());
        currentJob.setName(job.getName());
        currentJob.setSort(job.getSort());
        JobDto jobDto = jobMapper.toDto(currentJob);
        return jobDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delByJob(Long id) {
        Job currentJob = jobRepository.getOne(id);
        if (Objects.isNull(currentJob)) {
            throw  new EntityNotFoundException(Job.class, "id", id);
        }
        jobRepository.deleteById(id);
    }
}
