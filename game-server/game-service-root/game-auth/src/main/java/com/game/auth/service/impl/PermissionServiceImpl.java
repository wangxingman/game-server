package com.game.auth.service.impl;

import com.game.auth.mapper.PermissionMapper;
import com.game.auth.repository.PermisssionRepository;
import com.game.auth.service.PermissionService;
import com.game.common.constant.Const;
import com.game.common.dto.user.PermissionDto;
import com.game.common.entity.user.Permission;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author : wx
 * @Desc :  权限的验证
 * @Date :  下午 6:13 2019/7/1 0001
 * @explain :
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermisssionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    private static final String ENTITY_NAME = "permission";

    @Override
    public Object getByAllTree(List<Permission> permissions) {
        List<Map<String, Object>> list = new LinkedList<>();
        permissions.forEach(permission -> {
            if(Objects.nonNull(permission)) {
                //获取上级的目录
                List<Permission> permissionList = permissionRepository.findByPid(permission.getId());
                Map<String,Object> map = new HashMap<>();
                map.put("id",permission.getId());
                map.put("label",permission.getAlias());
                if(Objects.nonNull(permissionList) && permissionList.size()!= Const.number.ZERO) {
                    map.put("children",getByAllTree(permissionList));
                }
                list.add(map);
            }
        });
        return list;
    }

    @Override
    public List<Permission> findByPid(long pid) {
        List<Permission> permissionList = permissionRepository.findByPid(pid);
        if(Objects.isNull(permissionList)) {
            throw new EntityNotFoundException(Permission.class,"pid",pid);
        }
        return permissionList;
    }

    @Override
    public List<PermissionDto> queryAll() {
        List<Permission> permissionList = permissionRepository.findAll();
        if(Objects.isNull(permissionList)) {
            throw new EntityNotFoundException(Permission.class);
        }
        List<PermissionDto> permissionDtos = permissionMapper.toDto(permissionList);
        return permissionDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PermissionDto addByPermission(Permission permission) {
        if(Objects.nonNull(permission.getId())) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        if(permissionRepository.findByName(permission.getName()) != null){
            throw new EntityExistException(Permission.class,"name",permission.getName());
        }
        return permissionMapper.toDto(permissionRepository.save(permission));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PermissionDto updateByPermissions(Permission permission) {
        Permission currentPermission = permissionRepository.getOne(permission.getId());
        if(permission.getId().equals(permission.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        Permission permission1 = permissionRepository.findByName(permission.getName());
        //表示不是同一条数据
        if(permission1 != null && !permission1.getId().equals(permission.getId())){
            throw new EntityExistException(Permission.class,"name",permission.getName());
        }
        currentPermission.setName(permission.getName());
        currentPermission.setAlias(permission.getAlias());
        currentPermission.setPid(permission.getPid());
        permissionRepository.saveAndFlush(permission);
        PermissionDto permissionDto = permissionMapper.toDto(currentPermission);
        return  permissionDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delByPermissions(Long id) {
        List<Permission> permissionList = permissionRepository.findByPid(id);
        for (Permission permission : permissionList) {
            permissionRepository.delete(permission);
        }
        permissionRepository.deleteById(id);
    }

}
