package com.jj.managesys.service;

import java.util.List;

/**
 * @author huangjunjie
 * @ClassName CrudService
 * @Description
 * @Date 2017/12/26.
 */
public interface CrudService<T> {

    T selectById(long id);

    List<T> selectAll();

    int save(T t);

    int update(T t);

    int delete(long id);

}
