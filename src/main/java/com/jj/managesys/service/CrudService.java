package com.jj.managesys.service;

import com.jj.managesys.common.exceptions.BadRequestException;

import java.util.Map;


/**
 * @author huangjunjie
 * @ClassName CrudService
 * @Description
 * @Date 2017/12/26.
 */
public interface CrudService<T> {

    T selectById(long id, String token);

    Map selectAll(int pageNum, int pageSize, String token);

    int save(T t, String token) throws BadRequestException;

    int update(T t, String token) throws BadRequestException;

    int delete(long id, String token) throws BadRequestException;

}
