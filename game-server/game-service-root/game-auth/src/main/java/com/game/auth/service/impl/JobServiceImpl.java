package com.game.auth.service.impl;

import com.game.auth.repository.JobRepository;
import com.game.auth.service.JobService;
import com.game.common.entity.user.Job;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import com.game.core.utils.jpa.QueryHelp;
import com.game.core.utils.jpa.criteria.auth.JobQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:53 2019/7/2 0002
 * @explain :
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Object findBySearchAll(JobQueryCriteria criteria, Pageable pageable) {
        Page<Job> page = jobRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        if (Objects.isNull(page)) {
            throw new BadRequestException("jobList为null");
        }
        List<Job> jobs = new ArrayList<>();
        for (Job job : page.getContent()) {
            jobs.add(job);
        }
        List<Job> jobList = jobRepository.findAll();
        int count = jobList.size();
        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("jobs",jobs);
        return map;
    }

    @Override
    public Object findByAll() {
        List<Job> repositoryAll = jobRepository.findAll();
        if (Objects.isNull(repositoryAll)) {
            throw new BadRequestException("jobList为null");
        }
        return repositoryAll;
    }

    @Override
    public Job findById(Long id) {
        Job job = jobRepository.getOne(id);
        if (Objects.isNull(job)) {
            throw new EntityNotFoundException(Job.class, "id", id);
        }
        return job;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Job addByJob(Job job) {
        Job currentJob = jobRepository.findByName(job.getName());
        if (Objects.nonNull(currentJob)) {
            throw new EntityExistException(Job.class, "name", job.getName());
        }
        return jobRepository.save(job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Job updateByJob(Job job) {
        Optional<Job> job1 = jobRepository.findById(job.getId());
        Job currentJob = job1.get();
        if (Objects.isNull(currentJob)) {
            throw new EntityNotFoundException(Job.class, "id", job.getId());
        }
        currentJob.setSort(job.getSort());
        currentJob.setName(job.getName());
        currentJob.setDept(job.getDept());
        currentJob.setEnabled(job.getEnabled());
        return jobRepository.saveAndFlush(job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delByJob(Long id) {
        Job currentJob = jobRepository.getOne(id);
        if (Objects.isNull(currentJob)) {
            throw new EntityNotFoundException(Job.class, "id", id);
        }
        jobRepository.deleteById(id);
    }
}
