package com.SOB.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;

import com.SOB.bean.PageBean;
import com.SOB.bean.UserBean;

public interface UserDaoIface {

	//用户登录身份验证
	public UserBean findUserInfo(Connection con,String id,String pwd);
	
	//新用户信息注册
	public int insertNewUser(Connection con,UserBean user);
	
	//查询所有用户信息
	public ResultSet userList(Connection con,PageBean pageBean,UserBean ub)throws Exception;
	//查询符合条件的用户数量
	public int userCount(Connection con,PageBean pageBean,UserBean ub)throws Exception;
	
	//根据用户的id删除用户信息
	public int deleteUserInforById(Connection con,String id )throws Exception;
	
	//修改用户信息
	public int modifyUserInfor(Connection con,UserBean bean,String userid);
}
