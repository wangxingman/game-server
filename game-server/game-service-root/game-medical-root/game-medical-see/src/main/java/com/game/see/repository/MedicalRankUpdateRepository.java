package com.game.see.repository;

import com.game.see.entity.MedicalRankUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:38 2019/8/9 0009
 * @explain :
 */
@Repository
public interface MedicalRankUpdateRepository extends JpaRepository<MedicalRankUpdate,Long>, JpaSpecificationExecutor {
}
