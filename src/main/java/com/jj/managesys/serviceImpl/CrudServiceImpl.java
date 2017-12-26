/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.serviceImpl;

import com.jj.managesys.mapper.CrudMapper;
import com.jj.managesys.service.CrudService;

import java.util.List;

/**
 * @author huangjunjie
 * @ClassName CrudServiceImpl
 * @Description
 * @Date 2017/12/26.
 */
public abstract class CrudServiceImpl<T> implements CrudService<T> {


    public abstract CrudMapper<T> getMapper();

    @Override
    public T selectById(long id) {
        return this.getMapper().selectById(id);
    }

    @Override
    public List<T> selectAll() {
        return this.getMapper().selectAll();
    }

    @Override
    public int save(T t) {
        return this.getMapper().save(t);
    }

    @Override
    public int update(T t) {
        return this.getMapper().update(t);
    }

    @Override
    public int delete(long id) {
        return this.getMapper().delete(id);
    }
}
