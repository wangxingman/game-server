package com.game.auth.service;

import com.game.common.dto.user.DictDto;
import com.game.common.entity.user.Dict;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:22 2019/7/2 0002
 * @explain :
 */
@CacheConfig(cacheNames = "dict")
public interface DictService {

   /**
    * @Author: wx
    * @Date  : 下午 5:20 2019/7/2 0002 
    * @params: 
    * @Desc  :
    */
    Object findByAll(DictDto dict, Pageable pageable);

    /**
     * @Author: wx
     * @Date  : 下午 5:20 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    @Cacheable(key = "#p0")
    DictDto findById(Long id);

   /**
    * @Author: wx
    * @Date  : 下午 5:19 2019/7/2 0002 
    * @params: 
    * @Desc  :
    */
    @CacheEvict(allEntries = true)
    DictDto addByDict(Dict dict);

    /**
     * @Author: wx
     * @Date  : 下午 5:19 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    @CacheEvict(allEntries = true)
    DictDto updateByDict(Dict dict);

    /**
     * @Author: wx
     * @Date  : 下午 5:20 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    @CacheEvict(allEntries = true)
    void deleteByDict(Long id);
    
}
