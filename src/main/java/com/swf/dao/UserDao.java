package com.swf.dao;

import com.swf.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author swf
 * @since 2020-04-02 22:10:32
 */
public interface UserDao extends JpaSpecificationExecutor<User>, PagingAndSortingRepository<User,Long> {

	/**
	 * 	根据名字查找用户信息
	 * @param name
	 * @return
	 */
	User findByName(String name);



}
