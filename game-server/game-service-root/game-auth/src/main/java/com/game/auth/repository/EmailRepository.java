package com.game.auth.repository;

import com.game.common.entity.email.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:32 2019/8/8 0008
 * @explain :
 */
@Repository
public interface EmailRepository extends JpaRepository<EmailConfig,Long> {
}
