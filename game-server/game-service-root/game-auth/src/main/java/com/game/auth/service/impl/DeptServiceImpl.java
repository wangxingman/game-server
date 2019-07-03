package com.game.auth.service.impl;

import com.game.auth.mapper.DeptMapper;
import com.game.auth.repository.DeptRepository;
import com.game.auth.service.DeptService;
import com.game.common.constant.Const;
import com.game.common.dto.user.DeptDto;
import com.game.common.entity.user.Dept;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:45 2019/7/2 0002
 * @explain :
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public List<DeptDto> findByAll() {
        List<Dept> deptList = deptRepository.findAll();
        if (Objects.isNull(deptList)) {
            throw new BadRequestException("deptList没有数据");
        }
        List<DeptDto> deptDtoList = deptMapper.toDto(deptList);
        return deptDtoList;
    }

    @Override
    public DeptDto findById(Long id) {
        Optional<Dept> repository = deptRepository.findById(id);
        Dept dept = repository.get();
        if (Objects.isNull(dept)) {
            throw new EntityNotFoundException(Dept.class, "id", id);
        }
        DeptDto deptDto = deptMapper.toDto(dept);
        return deptDto;
    }

    @Override
    public DeptDto addByDept(Dept dept) {
        Dept currentDept = deptRepository.findByName(dept.getName());
        if (Objects.nonNull(currentDept)) {
            throw new EntityExistException(Dept.class, "name", dept.getName());
        }
        DeptDto deptDto = deptMapper.toDto(deptRepository.save(dept));
        return deptDto;
    }

    @Override
    public DeptDto updateByDept(Dept dept) {
        if (dept.getId().equals(dept.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        Dept currentDept = deptRepository.getOne(dept.getId());
        if (Objects.isNull(currentDept)) {
            throw new EntityNotFoundException(Dept.class, "部门id", dept.getId());
        } else {
            currentDept.setName(dept.getName());
            currentDept.setRoles(dept.getRoles());
        }
        DeptDto deptDto = deptMapper.toDto(deptRepository.saveAndFlush(currentDept));
        return deptDto;
    }

    @Override
    public void deleteByDept(Long id) {
        try {
            deptRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("部门删除用户失败");
        }
    }

    @Override
    public Object findByAllTree(List<DeptDto> deptDtos) {
        Set<DeptDto> trees = new LinkedHashSet<>();
        Set<DeptDto> depts = new LinkedHashSet<>();
        List<String> deptNames = deptDtos.stream().map(DeptDto::getName).collect(Collectors.toList());
        Boolean isChild;
        for (DeptDto deptDto : deptDtos) {
            isChild = false;
            if (String.valueOf(Const.number.ZERO).equals(deptDto.getPid().toString())) {
                trees.add(deptDto);
            }
            for (DeptDto it : deptDtos) {
                if (it.getPid().equals(deptDto.getId())) {
                    isChild = true;
                    if (deptDto.getChildren() == null) {
                        deptDto.setChildren(new ArrayList<>());
                    }
                    deptDto.getChildren().add(it);
                }
            }
            if (isChild) {
                depts.add(deptDto);
            } else if (!deptNames.contains(deptRepository.findNameById(deptDto.getPid()))) {
                depts.add(deptDto);
            }
            if (CollectionUtils.isEmpty(trees)) {
                trees = depts;
            }
        }
        Integer totalElements = deptDtos != null ? deptDtos.size() : 0;
        Map map = new HashMap();
        map.put("totalElements", totalElements);
        map.put("content", CollectionUtils.isEmpty(trees) ? deptDtos : trees);
        return map;
    }

    @Override
    public List<Dept> findByPid(long pid) {
        List<Dept> deptList = deptRepository.findByPid(pid);
        return deptList;
    }

    @Override
    public Set<Dept> findByRoleIds(Long id) {
        return deptRepository.findByRoles_Id(id);
    }
}
