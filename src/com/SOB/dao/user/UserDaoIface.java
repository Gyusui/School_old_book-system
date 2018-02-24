package com.SOB.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;

import com.SOB.bean.PageBean;
import com.SOB.bean.UserBean;

public interface UserDaoIface {

	//�û���¼�����֤
	public UserBean findUserInfo(Connection con,String id,String pwd);
	
	//���û���Ϣע��
	public int insertNewUser(Connection con,UserBean user);
	
	//��ѯ�����û���Ϣ
	public ResultSet userList(Connection con,PageBean pageBean,UserBean ub)throws Exception;
	//��ѯ�����������û�����
	public int userCount(Connection con,PageBean pageBean,UserBean ub)throws Exception;
	
	//�����û���idɾ���û���Ϣ
	public int deleteUserInforById(Connection con,String id )throws Exception;
	
	//�޸��û���Ϣ
	public int modifyUserInfor(Connection con,UserBean bean,String userid);
}
