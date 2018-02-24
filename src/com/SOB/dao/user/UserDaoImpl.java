package com.SOB.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SOB.bean.PageBean;
import com.SOB.bean.UserBean;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.StringUtil;

public class UserDaoImpl  implements UserDaoIface{

	//用户登录身份验证
	public UserBean findUserInfo(Connection con,String id,String pwd){
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from USER_INFOR_TAB where userid=? and userpwd=?";
		UserBean user = new UserBean();
		try {
			pst = con.prepareStatement(sql);
			
			pst.setString(1,id);
			
			pst.setString(2,pwd);
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				String userId = rs.getString(2);
				String userPwd = rs.getString(3);
				String email = rs.getString(4);
				String right = rs.getString(5);
				
				user.setUserId(userId);
				user.setUserPwd(userPwd);
				user.setEmail(email);
				user.setUserRight(right);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataBaseConnManager.closeResultSet(rs);
			DataBaseConnManager.closePreparedStatement(pst);
		}
		return user;
	}
	
	//新用户信息注册
	public int insertNewUser(Connection con,UserBean user){
		String sql = "insert into USER_INFOR_TAB(userid,userpwd,email,userright) values (?,?,?,?)";
		PreparedStatement pst = null;
		int i = 0;
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,user.getUserId());
			pst.setString(2,user.getUserPwd());
			pst.setString(3,user.getEmail());
			pst.setString(4,user.getUserRight());
			
			i = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataBaseConnManager.closePreparedStatement(pst);
		}
		return i;
	}
	public ResultSet userList(Connection con,PageBean pageBean,UserBean ub)throws Exception{
		StringBuffer sb=new StringBuffer("select * from USER_INFOR_TAB where 1 = 1");
		if(StringUtil.isNotEmpty(ub.getUserId())){
			sb.append(" and userid = '"+ub.getUserId()+"'");
		}
		if(StringUtil.isNotEmpty(ub.getUserRight())){
			sb.append(" and userright = '"+ub.getUserRight()+"'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public int userCount(Connection con,PageBean pageBean,UserBean ub)throws Exception{
		
		StringBuffer sb=new StringBuffer("select count(*) as total from USER_INFOR_TAB where 1 = 1");
		if(StringUtil.isNotEmpty(ub.getUserId())){
			sb.append(" and userid = '"+ub.getUserId()+"'");
		}
		if(StringUtil.isNotEmpty(ub.getUserRight())){
			sb.append(" and userright = '"+ub.getUserRight()+"'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs =  pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
		
	}
	
	//根据用户的id删除用户信息
	public int deleteUserInforById(Connection con,String id )throws Exception{
		String sql = "delete from USER_INFOR_TAB where userid in ("+id+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
		
	}
	/*
	 * 修改用户信息
	 */
	public int modifyUserInfor(Connection con,UserBean bean,String userid){
		String sql = "update USER_INFOR_TAB set userid=?,userpwd=?,email=?,userright=? where userid=?";
		
		PreparedStatement pst = null;
		int a = 0;
		try{
			pst = con.prepareStatement(sql);
			pst.setString(1,bean.getUserId());
			pst.setString(2,bean.getUserPwd());
			pst.setString(3,bean.getEmail());
			pst.setString(4,bean.getUserRight());
			pst.setString(5, userid);

			a = pst.executeUpdate();
	
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DataBaseConnManager.closePreparedStatement(pst);
			DataBaseConnManager.closeConn(con);
		}
		return a;
	}
}
