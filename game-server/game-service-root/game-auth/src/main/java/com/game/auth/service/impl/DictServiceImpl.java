package com.game.auth.service.impl;

import com.game.auth.mapper.DictMapper;
import com.game.auth.repository.DictRepository;
import com.game.auth.service.DictService;
import com.game.common.dto.user.DictDto;
import com.game.common.entity.user.Dict;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:22 2019/7/2 0002
 * @explain :
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictRepository dictRepository;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public Object findByAll(DictDto dict, Pageable pageable) {
        Page<Dict> repositoryAll = dictRepository.findAll(pageable);
        List<Dict> dictList = repositoryAll.getContent();
        if(Objects.isNull(dictList)) {
            throw new BadRequestException("字典集合不存在");
        }
        return dictList;
    }

    @Override
    public DictDto findById(Long id) {
        Dict dict = dictRepository.getOne(id);
        if(Objects.isNull(dict)) {
           throw new EntityNotFoundException(Dict.class,"id",id);
        }
        return dictMapper.toDto(dict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDto addByDict(Dict dict) {
        Dict currentDict = dictRepository.findByName(dict.getName());
        if(Objects.nonNull(currentDict)) {
            throw new EntityExistException(Dict.class,"添加的字典",dict.getName());
        }
        return dictMapper.toDto(dictRepository.save(dict));
    }

    @Override
    public DictDto updateByDict(Dict dict) {
        Dict currentDict = dictRepository.getOne(dict.getId());
        if(Objects.isNull(currentDict)) {
          throw new EntityNotFoundException(Dict.class,"字典id",dict.getId());
        } else {
            //赋值数据
            currentDict.setDictDetails(dict.getDictDetails());
        }
        return  dictMapper.toDto(currentDict);
    }

    @Override
    public void deleteByDict(Long id) {
        try {
            dictRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("字典删除用户失败");
        }
    }
}
