package com.game.common.mapper.inter;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:28 2019/6/24 0024
 * @explain :
 */
public interface EntityMapper<D,E> {


    /**
     * DTO转Entity
     * @param dto
     * @return
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     * @param entity
     * @return
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     * @param dtoList
     * @return
     */
    List <E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     * @param entityList
     * @return
     */
    List <D> toDto(List<E> entityList);
}
