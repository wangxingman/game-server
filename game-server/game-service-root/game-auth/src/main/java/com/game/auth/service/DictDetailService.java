package com.game.auth.service;

import com.game.common.entity.user.DictDetail;
import com.game.core.utils.jpa.criteria.auth.DictDetailQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:30 2019/7/2 0002
 * @explain :
 */
@CacheConfig(cacheNames = "dictDetail")
public interface DictDetailService {

    /**
     * @Author: wx
     * @Date : 下午 2:32 2019/7/2 0002
     * @params:
     * @Desc :
     */
    @Cacheable(key = "#p0")
    DictDetail findById(Long id);

    /**
     * @Author: wx
     * @Date : 下午 2:32 2019/7/2 0002
     * @params:
     * @Desc :
     */
    @CacheEvict(allEntries = true)
    DictDetail addByDictDetail(DictDetail dictDetail);

    /**
     * @Author: wx
     * @Date : 下午 2:32 2019/7/2 0002
     * @params:
     * @Desc :
     */
    @CacheEvict(allEntries = true)
    DictDetail updateByDictDetail(DictDetail resources);

    /**
     * @Author: wx
     * @Date : 下午 2:32 2019/7/2 0002
     * @params:
     * @Desc :
     */
    @CacheEvict(allEntries = true)
    void deleteByDictDetail(Long id);

    /**
     * @Author: wx
     * @Date : 下午 2:39 2019/7/2 0002
     * @params:
     * @Desc : 全查询
     */
    Object findByAll(DictDetailQueryCriteria criteria,Pageable pageable);
}
