package com.swf.service;

import com.swf.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author swf
 * @since 2020-04-02 22:10:36
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param page 当前页
     * @param size 每页条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int page, int size);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    void deleteById(Long id);

    /**
     *  根据name查找用户
     * @param name
     */
    User findByName(String name);

}
