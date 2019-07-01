package com.jt.easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.easymall.mapper.UserMapper;
import com.jt.easymall.pojo.User;
import com.jt.easymall.util.MD5Util;
import com.jt.easymall.util.UUIDUtil;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public int checkUserName(String userName) {
		int exists=userMapper.checkUserName(userName);
		//select count(product_id) from t_user ...
		return exists; 
	}
	public int regist(User user) {
		//封装数据,缺少userId,type
		user.setUserId(UUIDUtil.getUUID());
		user.setUserType(0);
		user.setUserPassword(MD5Util.md5(user.getUserPassword()));
		return userMapper.insertUser(user);
	}
	public User login(User user){
		user.setUserPassword(MD5Util.md5(user.getUserPassword()));
		User exists=userMapper.checkLogin(user);
		return exists;
	}

}
