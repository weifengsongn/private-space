package com.swf.entity;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @program: private-space
 * @description:
 * @author: Wfsong
 * @create: 2020-04-02 21:12
 **/
@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "real_name")
	private String realName;
	private	String name;
	private String password;

}
