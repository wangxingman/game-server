package com.game.auth.service.impl;

import com.game.auth.repository.MenuRepository;
import com.game.auth.service.MenuService;
import com.game.common.entity.user.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;


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

    /**
     * 查询所有菜单
     *
     * @return 所有菜单
     */
    @Override
    @Cacheable
    public List<Menu> findAll() {
        List<Menu> menus = menuRepository.findAll();
        if(Objects.isNull(menus)) {
            log.info("菜单是null的！");
        }
        return menus;
    }

    @Override
    public List<Menu> findByPid(long pid) {
        return menuRepository.findByPid(pid);
    }

    @Override
    public Object getMenuTree(List<Menu> menus) {
        List<Map<String,Object>> list = new LinkedList<>();
        menus.forEach(menu -> {
                    if (menu!=null){
                        List<Menu> menuList = menuRepository.findByPid(menu.getId());
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",menu.getId());
                        map.put("label",menu.getName());
                        map.put("path",menu.getComponent());
                        if(menuList!=null && menuList.size()!=0){
                            map.put("children",getMenuTree(menuList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    
}
