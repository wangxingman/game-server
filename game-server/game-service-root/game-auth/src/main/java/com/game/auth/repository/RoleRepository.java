package com.game.auth.repository;

import com.game.common.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:51 2019/6/24 0024
 * @explain :
 */
public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor {

    Role findByName(String name);

    Set<Role> findByUsers_Id(Long id);

}
