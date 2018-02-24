package com.SOB.action.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.SOB.bean.UserBean;
import com.SOB.dao.user.UserDaoIface;
import com.SOB.dao.user.UserDaoImpl;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.ResponseUtil;

public class AddUserAction {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		Connection con = DataBaseConnManager.getConnection_2();
		String id = request.getParameter("userid");
		String pwd = request.getParameter("userpwd");
		String email = request.getParameter("email");
		String right = request.getParameter("userright");
		UserBean ub = new UserBean(id,pwd,email,right);
		UserDaoIface userDao = new UserDaoImpl();
		JSONObject jb = new JSONObject();
		try{
			int bl = userDao.insertNewUser(con, ub);
			if(bl > 0){
				jb.put("success", "true");
			}else {
				jb.put("errorMsg", "ÃÌº” ß∞‹");
			}
			ResponseUtil.write(response, jb);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DataBaseConnManager.closeConn(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
