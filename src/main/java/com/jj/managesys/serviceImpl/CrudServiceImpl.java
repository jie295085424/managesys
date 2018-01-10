package com.jj.managesys.serviceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.managesys.common.exceptions.BadRequestException;
import com.jj.managesys.mapper.CrudMapper;
import com.jj.managesys.service.CrudService;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangjunjie
 * @ClassName CrudServiceImpl
 * @Description
 * @Date 2017/12/26.
 */
public abstract class CrudServiceImpl<T> implements CrudService<T> {


    public abstract CrudMapper<T> getMapper();

    @Override
    public T selectById(long id, String token) {
        return this.getMapper().selectById(id);
    }

    @Override
    public Map selectAll(int pageNum, int pageSize, String token)
    {
        Page<T> p = PageHelper.startPage(pageNum, pageSize, true).doSelectPage(() -> this.getMapper().selectAll());
        Map map = new HashMap();
        map.put("count", p.getTotal());
        map.put("dataList", p.getResult());
        return map;
    }

    @Override
    public int save(T t, String token) throws BadRequestException {
        return this.getMapper().save(t);
    }

    @Override
    public int update(T t, String token) throws BadRequestException {
        return this.getMapper().update(t);
    }

    @Override
    public int delete(long id, String token) throws BadRequestException {
        return this.getMapper().delete(id);
    }
}
