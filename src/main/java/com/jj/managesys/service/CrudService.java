package com.jj.managesys.service;

import com.github.pagehelper.Page;
import com.jj.managesys.common.exceptions.BadRequestException;


/**
 * @author huangjunjie
 * @ClassName CrudService
 * @Description
 * @Date 2017/12/26.
 */
public interface CrudService<T> {

    T selectById(long id, String token);

    Page<T> selectAll(int pageNum, int pageSize, String token);

    int save(T t, String token) throws BadRequestException;

    int update(T t, String token) throws BadRequestException;

    int delete(long id, String token) throws BadRequestException;

}
