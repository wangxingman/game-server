package com.game.auth.service.impl;

import com.game.auth.repository.MenuRepository;
import com.game.auth.service.MenuService;
import com.game.common.entity.user.Menu;
import com.game.common.entity.user.Role;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityExistException;
import com.game.core.utils.jpa.criteria.CommonQueryCriteria;
import com.game.core.utils.jpa.QueryHelp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:53 2019/6/20 0020
 * @explain :
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findCheckPermission() {
        return null;
    }

    @Override
    public List<Menu> findByPid(long pid) {
        return menuRepository.findByPid(pid);
    }

    @Override
    public Object getMenuTree(List<Menu> menus) {
        return buildObjTree(menus);
    }

    private Object buildObjTree(List<Menu> menus) {
        List<Map<String, Object>> list = new LinkedList<>();
        menus.forEach(menu -> {
                    if (menu != null) {
                        List<Menu> menuList = menuRepository.findByPid(menu.getId());
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", menu.getId());
                        map.put("label", menu.getName());
                        map.put("path", menu.getComponent());
                        if (menuList != null && menuList.size() != 0) {
                            map.put("children", getMenuTree(menuList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    @Override
    public List<Menu> findByMenuToRoles(List<Role> roles) {
        Set<Menu> menus = new LinkedHashSet<>();
        for (Role role : roles) {
            List<Menu> menuList = menuRepository.findByRoles_IdOrderBySortAsc(role.getId()).stream().collect(Collectors.toList());
            menus.addAll(menuList);
        }
        return menus.stream().collect(Collectors.toList());
    }

    @Override
    public Object buildTree(List<Menu> menus) {
        Object buildObjTree = this.buildObjTree(menus);
        return buildObjTree;
    }

    @Override
    public List<Menu> getByAllSearch(CommonQueryCriteria criteria) {
        List menuRepositoryAll = menuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
        return menuRepositoryAll;
    }

    @Override
    public Menu addByMenu(Menu menu) {
        if (menu.getId() != null) {
            throw new BadRequestException("您添加的menu的ID");
        }
        if (menuRepository.findByName(menu.getName()) != null) {
            throw new EntityExistException(Menu.class, "name", menu.getName());
        }
        return menuRepository.save(menu);
    }

    @Override
    public Menu updateByMenu(Menu menu) {
        if(menu.getId().equals(menu.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        Optional<Menu> optionalPermission = menuRepository.findById(menu.getId());
        Menu currentMenu = optionalPermission.get();
        Menu menu1 = menuRepository.findByName(currentMenu.getName());

        if(menu1 != null && !menu1.getId().equals(currentMenu.getId())){
            throw new EntityExistException(Menu.class,"name",currentMenu.getName());
        }
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public void delByMenu(Long id) {
        List<Menu> menuList = menuRepository.findByPid(id);
        for (Menu menu : menuList) {
            if(Objects.nonNull(menu)) {
                menuRepository.deleteById(menu.getId());
            }
        }
    }

}
