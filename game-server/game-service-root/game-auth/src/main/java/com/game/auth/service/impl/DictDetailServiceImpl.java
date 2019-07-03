package com.game.auth.service.impl;

import com.game.auth.mapper.DictDetailMapper;
import com.game.auth.repository.DictDetailRepository;
import com.game.auth.service.DictDetailService;
import com.game.common.dto.user.DictDetailDto;
import com.game.common.entity.user.DictDetail;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:42 2019/7/2 0002
 * @explain :
 */
@Service
public class DictDetailServiceImpl implements DictDetailService {

    @Autowired
    private DictDetailMapper dictDetailMapper;

    @Autowired
    private DictDetailRepository dictDetailRepository;


    @Override
    public DictDetailDto findById(Long id) {
        Optional<DictDetail> detailOptional = dictDetailRepository.findById(id);
        DictDetail dictDetail = detailOptional.get();
        if (Objects.isNull(dictDetail)) {
            throw new EntityNotFoundException(DictDetail.class, "id", id);
        }
        return dictDetailMapper.toDto(dictDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDetailDto addByDictDetail(DictDetail dictDetail) {
        return dictDetailMapper.toDto(dictDetailRepository.save(dictDetail));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDetailDto updateByDictDetail(DictDetail resources) {
        DictDetail dictDetail = dictDetailRepository.getOne(resources.getId());
        if (Objects.isNull(dictDetail)) {
            throw new EntityNotFoundException(DictDetail.class, "id", resources.getId());
        } else {
            resources.setDict(resources.getDict());
            DictDetail dictDetail1 = dictDetailRepository.saveAndFlush(resources);
            return  dictDetailMapper.toDto(dictDetail1);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByDictDetail(Long id) {
        dictDetailRepository.deleteById(id);
    }

    @Override
    public Object findByAll(Pageable pageable) {
        Page<DictDetail> dictDetailPage = dictDetailRepository.findAll(pageable);
        if(Objects.isNull(dictDetailPage)) {
          throw new BadRequestException("dictDetailPage没有数据");
        }
        return dictDetailPage.getContent();
    }

}
