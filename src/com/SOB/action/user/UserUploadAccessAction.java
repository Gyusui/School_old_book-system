package com.SOB.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserUploadAccessAction {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id") !=null){
		      response.setCharacterEncoding("UTF-8");
		      PrintWriter out = response.getWriter();
		      out.print("{\"msg\":\"打开上传窗口\"}");
		}else{
		      response.setCharacterEncoding("UTF-8");
		      PrintWriter out = response.getWriter();
		      out.print("{\"error\":\"true\"}");
		}
	}

}
