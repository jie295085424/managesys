package com.jj.managesys.mapper;

import java.util.List;

/**
 * @author huangjunjie
 * @ClassName
 * @Description
 * @Date 2017/12/26.
 */

public interface CrudMapper<T> {

    T selectById(long id);

    List<T> selectAll();

    int save(T t);

    int update(T t);

    int delete(long id);
}
