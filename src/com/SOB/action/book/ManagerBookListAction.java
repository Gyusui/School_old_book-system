package com.SOB.action.book;

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
import com.SOB.dao.book.BooksDaoIface;
import com.SOB.dao.book.BooksDaoImpl;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.JsonUtil;
import com.SOB.util.ResponseUtil;

public class ManagerBookListAction {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		Connection con = DataBaseConnManager.getConnection_2();
		
		HttpSession session = request.getSession();
		
		String bookName = request.getParameter("bookName");
		String grade = request.getParameter("grade");
		String bookObject = request.getParameter("bookObject");
		
		BooksDaoIface bookDao = new BooksDaoImpl();
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		try{
			ResultSet rslt = bookDao.managerBookList(con, pageBean,bookName, grade, bookObject); 
			JSONObject result = new JSONObject();
			JSONArray ja = JsonUtil.formatRsToJsonArray(rslt);
			result.put("rows", ja);
			result.put("total", bookDao.managerBookCount(con, pageBean,bookName, grade, bookObject));
			ResponseUtil.write(response, result);
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
