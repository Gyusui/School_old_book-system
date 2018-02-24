package com.SOB.action.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.SOB.bean.PageBean;
import com.SOB.bean.UserBean;
import com.SOB.dao.user.UserDaoIface;
import com.SOB.dao.user.UserDaoImpl;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.JsonUtil;
import com.SOB.util.ResponseUtil;

public class DeleteUserAction {
	
public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		Connection con = DataBaseConnManager.getConnection_2();
		String id = request.getParameter("userid");
		UserDaoIface userDao = new UserDaoImpl();
		JSONObject jb = new JSONObject();
		try{
			int bl = userDao.deleteUserInforById(con, id);
			if(bl > 0){
				jb.put("success", "true");
				jb.put("delNums", bl);
			}else {
				jb.put("errorMsg", "É¾³ýÊ§°Ü");
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
