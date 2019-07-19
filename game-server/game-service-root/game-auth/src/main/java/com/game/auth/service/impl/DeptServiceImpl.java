package com.game.auth.service.impl;

import com.game.auth.mapper.DeptMapper;
import com.game.auth.repository.DeptRepository;
import com.game.auth.service.DeptService;
import com.game.common.constant.Const;
import com.game.common.dto.user.DeptDto;
import com.game.common.entity.user.Dept;
import com.game.common.entity.user.Menu;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import com.game.core.utils.jpa.QueryHelp;
import com.game.core.utils.jpa.criteria.DeptQueryCriteria;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public List<Dept> queryAll(DeptQueryCriteria criteria) {
        return deptRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
    }

    private Object buildObjTree(List<Dept> depts) {
        List<Map<String, Object>> list = new LinkedList<>();
        depts.forEach(dept -> {
                    if (dept != null) {
                        List<Dept> menuList = deptRepository.findByPid(dept.getId());
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", dept.getId());
                        map.put("label", dept.getName());
                        if (menuList != null && menuList.size() != 0) {
                            map.put("children", buildObjTree(menuList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    @Override
    public Object findByAll() {
        List<Dept> deptList = deptRepository.findAll();
        if(Objects.isNull(deptList)) {
           log.info("没有部门数据");
        }
        return buildObjTree(deptList);
    }

    @Override
    public Dept findById(Long id) {
        Optional<Dept> repository = deptRepository.findById(id);
        Dept dept = repository.get();
        if (Objects.isNull(dept)) {
            throw new EntityNotFoundException(Dept.class, "id", id);
        }
        return dept;
    }

    @Override
    public Dept addByDept(Dept dept) {
        Dept currentDept = deptRepository.findByName(dept.getName());
        if (Objects.nonNull(currentDept)) {
            throw new EntityExistException(Dept.class, "name", dept.getName());
        }
        return deptRepository.save(dept);
    }

    @Override
    public Dept updateByDept(Dept dept) {
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
        return deptRepository.saveAndFlush(currentDept);
    }

    @Override
    public void delByDept(Long id) {
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
