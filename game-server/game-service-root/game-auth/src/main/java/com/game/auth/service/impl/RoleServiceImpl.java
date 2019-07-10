package com.game.auth.service.impl;

import cn.hutool.core.util.PageUtil;
import com.game.auth.mapper.RoleMapper;
import com.game.auth.repository.RoleRepository;
import com.game.auth.service.RoleService;
import com.game.common.dto.user.RoleDto;
import com.game.common.dto.user.RoleSmallDto;
import com.game.common.entity.user.Role;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import com.game.core.utils.jpa.CommonQueryCriteria;
import com.game.core.utils.jpa.JpaPageUtil;
import com.game.core.utils.jpa.QueryHelp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/6/24 0024
 * @explain :
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findById(long id) {
        Role role = roleRepository.findById(id).get();
        if (Objects.isNull(role)) {
            throw new EntityNotFoundException(Role.class, "id", id);
        }
        return role;
    }

    @Override
    public Object findByAll(Pageable pageable) {
        List<Role> roleList = roleRepository.findAll(pageable).getContent();
        if (Objects.isNull(roleList)) {
            throw new EntityNotFoundException(Role.class, "roleList", roleList);
        }
        return roleList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role updatebyRole(Role role) {
        Optional<Role> optionalRole = roleRepository.findById(role.getId());
        Role currentRole = optionalRole.get();

        Role currentRoleName = roleRepository.findByName(role.getName());

        if(Objects.nonNull(currentRole)  && currentRoleName!=currentRole )  {
           throw new EntityExistException(Role.class,"role_id",role.getId(),"role_name",role.getName());
        }
        currentRole.setName(role.getName());
        currentRole.setRemark(role.getRemark());
        currentRole.setDataScope(role.getDataScope());
        currentRole.setDepts(role.getDepts());
        currentRole.setLevel(role.getLevel());

        Role role1 = roleRepository.saveAndFlush(currentRole);
        return role1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role updateByRoleMenu(Role resources, Role currRole) {
        currRole.setMenus(resources.getMenus());
        Role currentRole = roleRepository.saveAndFlush(currRole);
        return currentRole;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRole(Long id) {
        if(Objects.isNull(id)) {
            throw new NullPointerException("删除用户的id为null");
        }
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role addByRole(Role role) {
        Role currentRole = roleRepository.findByName(role.getName());
        if(Objects.nonNull(currentRole) ) {
            throw new EntityExistException(Role.class,"username",role.getName());
        }
        return roleRepository.save(role);
    }

    @Override
    public Role updateByRolePermission(Role role, Role currRole) {
        currRole.setPermissions(role.getPermissions());
        Role role1 = roleRepository.saveAndFlush(role);
        return  role1;
    }

    @Override
    public Object findByAllSearch(CommonQueryCriteria criteria, Pageable pageable) {
        Page<Role> page = roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return JpaPageUtil.toPage(page);
    }

}
