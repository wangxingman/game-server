package com.game.auth.mapper;

import com.game.common.entity.user.Permission;
import com.game.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/5/30 0030
 * @explain :
 */
@Repository
public interface PermisssionMapper extends JpaRepository<Permission,Integer>,JpaSpecificationExecutor {

}
