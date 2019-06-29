package com.game.auth.repository;

import com.game.common.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/5/30 0030
 * @explain :
 */
@Repository
public interface PermisssionRepository extends JpaRepository<Permission,Integer>,JpaSpecificationExecutor {

}
