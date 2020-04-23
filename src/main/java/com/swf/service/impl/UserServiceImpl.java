package com.swf.service.impl;

import com.swf.entity.User;
import com.swf.dao.UserDao;
import com.swf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author swf
 * @since 2020-04-02 22:10:37
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.findById(id).get();
    }

    /**
     * 查询多条数据
     *
     *@param page 当前页
     *@param size 每页条数
     * @return 对象列表
     */
    @Override
    public Page<User> queryAllByLimit(int page, int size, String sort, String sortField) {
//        页码、每页数据、排序规则、排序字段
        Pageable pageable = PageRequest.of(page,size, "id".equals(sortField)? Sort.Direction.DESC: Sort.Direction.fromString(sort), sortField);
        Page<User> all = userDao.findAll(pageable);
        return all;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.save(user);
        return user;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(Long id) {
        this.userDao.deleteById(id);
    }

    @Override
    public void deleteByEntitys(List<User> user) {
        userDao.deleteAll(user);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }
}
