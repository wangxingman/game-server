package com.game.production.mapper;

import com.game.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@Repository
public interface DemoMapper extends JpaRepository<Demo,Integer> {
    
}
