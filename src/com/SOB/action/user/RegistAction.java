package com.SOB.action.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SOB.bean.UserBean;
import com.SOB.dao.user.UserDaoIface;
import com.SOB.dao.user.UserDaoImpl;
import com.SOB.util.DataBaseConnManager;



public class RegistAction {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		Connection con = DataBaseConnManager.getConnection_2();
		
		HttpSession session = request.getSession();
		
		String id = request.getParameter("userId");
		
		String pwd = request.getParameter("userPwd");
		
		String email = request.getParameter("email");
		
		UserDaoIface userDao = new UserDaoImpl();
		
		
		UserBean user = new UserBean(id,pwd,email,"0");
		userDao.insertNewUser(con, user);
		String msg = "注册成功";
		request.setAttribute("registMessage", msg);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
//		if(user.getLogin_id()!=null && user.getPwd()!=null){
//			String name = user.getName();
//			session.setAttribute("user_name",name);
//			String right = user.getUser_right();
//			
//			if(right.equals("1")){	//设定right = 1 为普通用户
//				RequestDispatcher rd = request.getRequestDispatcher("jsp/clientMain.jsp");
//				rd.forward(request, response);
//			}
//			else{
////				//设定right = 0 为高级管理用户
////				CategoryDaoIface cateDao = new CategoryDaoImpl();
////				SortBusinessImpl cateService = new SortBusinessImpl();
////				cateService.setCategoryDao(cateDao);
////				List<CategoryBean> cateList = cateService.getAllCategories(con);
////				session.setAttribute("cate_list", cateList);
////				
////				RequestDispatcher rd = request.getRequestDispatcher("jsp/adminMain.jsp");
////				rd.forward(request, response);
//			}
//		}else{
//			String msg = "<font color='red' size='3'>登录失败！请核实您的登录id和登录密码的正确性</font>&nbsp;&nbsp;或请先<a href='jsp/regist.jsp'>注册</a>";
//			request.setAttribute("login_msg", msg);
//			RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
//			rd.forward(request, response);
//		}
		
	}

}
