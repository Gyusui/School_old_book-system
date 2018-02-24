package com.SOB.action.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SOB.bean.BookBean;
import com.SOB.bean.UserBean;
import com.SOB.dao.book.BooksDaoIface;
import com.SOB.dao.book.BooksDaoImpl;
import com.SOB.dao.user.UserDaoIface;
import com.SOB.dao.user.UserDaoImpl;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.ResultSetToList;

public class LoginAction {

	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		Connection con = DataBaseConnManager.getConnection_2();
		
		HttpSession session = request.getSession();
		
		String id = request.getParameter("userId");
		
		String pwd = request.getParameter("userPwd");
		
		UserDaoIface userDao = new UserDaoImpl();
		UserBean user = userDao.findUserInfo(con, id, pwd);
		List rsList = null;
		try{
			if(user.getUserId()!=null && user.getUserPwd()!=null){
				session.setAttribute("user_id",user.getUserId());
				String right = user.getUserRight();
				if(right.equals("0")){	//设定right = 0为普通用户
					BooksDaoIface bookDao = new BooksDaoImpl();
					List<BookBean> list = new <BookBean>ArrayList();
					ResultSetToList rstl = new ResultSetToList();
					ResultSet rs = bookDao.managerBookList(con, null, null, null, null);
					rsList = rstl.resultSetSetToList(rs);
					Iterator it = rsList.iterator();
					while(it.hasNext()){
						Map hm = (Map)it.next();
						String price = String.valueOf((Double)hm.get("price"));
						BookBean bb = new BookBean();
						bb.setBookName((String)hm.get("bookname"));
						bb.setGrade((String)hm.get("grade"));
						bb.setBookObject((String)hm.get("bookobject"));
						bb.setPhysical((String)hm.get("physical"));
						bb.setDescription((String)hm.get("description"));
						bb.setPrice(price);
						bb.setPictureUrl((String)hm.get("picture"));
						list.add(bb);
					}
					request.getSession().setAttribute("indexBookList", list);
					RequestDispatcher rd = request.getRequestDispatcher("resource/jsp/UserMain.jsp");
					rd.forward(request, response);
				}else if(right.equals("1")){
					RequestDispatcher rd = request.getRequestDispatcher("resource/jsp/ManagerMain.jsp");
					rd.forward(request, response);
				}else{
					String msg = "登录失败！请核实您的登录id和登录密码的正确性";
					request.setAttribute("loginMessage", msg);
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
			}else{
				String msg = "登录失败！请核实您的登录id和登录密码的正确性";
				request.setAttribute("loginMessage", msg);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
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
