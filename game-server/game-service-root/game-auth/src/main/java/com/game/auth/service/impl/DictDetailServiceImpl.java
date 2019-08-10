package com.game.auth.service.impl;

import com.game.auth.repository.DictDetailRepository;
import com.game.auth.service.DictDetailService;
import com.game.common.entity.user.DictDetail;
import com.game.core.exception.EntityNotFoundException;
import com.game.core.utils.jpa.QueryHelp;
import com.game.core.utils.jpa.criteria.auth.DictDetailQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    private DictDetailRepository dictDetailRepository;


    @Override
    public DictDetail findById(Long id) {
        Optional<DictDetail> detailOptional = dictDetailRepository.findById(id);
        DictDetail dictDetail = detailOptional.get();
        if (Objects.isNull(dictDetail)) {
            throw new EntityNotFoundException(DictDetail.class, "id", id);
        }
        return dictDetail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDetail addByDictDetail(DictDetail dictDetail) {
        return dictDetailRepository.save(dictDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDetail updateByDictDetail(DictDetail resources) {
        DictDetail dictDetail = dictDetailRepository.getOne(resources.getId());
        if (Objects.isNull(dictDetail)) {
            throw new EntityNotFoundException(DictDetail.class, "id", resources.getId());
        } else {
            resources.setDict(resources.getDict());
            DictDetail dictDetail1 = dictDetailRepository.saveAndFlush(resources);
            return dictDetail1;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByDictDetail(Long id) {
        dictDetailRepository.deleteById(id);
    }

    @Override
    public Object findByAll(DictDetailQueryCriteria criteria,Pageable pageable) {
        Page<DictDetail> page = dictDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<DictDetail> detailList = page.getContent();
        return detailList;
    }

}
