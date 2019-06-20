package com.game.auth.service.impl;

import com.game.auth.MapperConfig.AuthMapperConfig;
import com.game.auth.service.MenuService;
import com.game.common.entity.user.Menu;
import com.game.common.entity.user.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:53 2019/6/20 0020
 * @explain :
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Override
    public List<Menu> findCheckPermission() {
        List<Menu> menuList = AuthMapperConfig.getInstance().getMenuMapper().findAll();
        checkMenu(menuList);
        return menuList;
    }

    /**
     * @Author: wx
     * @Date  : 上午 10:18 2019/6/20 0020 
     * @params: 
     * @Desc  :
     */
    private void checkMenu(List<Menu> menuList) {
        if(Objects.nonNull(menuList)) {
            for (int i = 0; i < menuList.size(); i++) {
                Menu menu = menuList.get(i);
                if(Objects.nonNull(menu)) {
                    Permission permission = menu.getPermission();
                    String permissionName = permission.getPermissionName();
                    if(Objects.nonNull(permissionName)) {
                        menuList.remove(i);
                        i--;
                        continue;
                    }
                    List<Menu> children = menu.getChildren();
                    checkMenu(children);
                }
            }
        }
    }

}
