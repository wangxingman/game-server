package com.game.auth.repository;

import com.game.common.entity.user.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:45 2019/7/2 0002
 * @explain :
 */
public interface DictDetailRepository extends JpaRepository<DictDetail, Long>, JpaSpecificationExecutor {

}
