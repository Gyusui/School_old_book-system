package com.SOB.action.book;

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

import com.SOB.bean.BookBean;
import com.SOB.dao.book.BooksDaoIface;
import com.SOB.dao.book.BooksDaoImpl;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.ResultSetToList;

public class ListBooksAction {

	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Connection con = DataBaseConnManager.getConnection_2();
		BooksDaoIface bookDao = new BooksDaoImpl();
		String grade = request.getParameter("grade");
		if(grade!=null){
			grade = new String(grade.getBytes("iso-8859-1"),"UTF-8");
			if(grade.equals("none")){
				grade = null;
			}
		}
		String bookObject = request.getParameter("bookobject");
		if(bookObject != null){
			bookObject = new String(bookObject.getBytes("iso-8859-1"),"UTF-8");
			if(bookObject.equals("none")){
				bookObject = null;
			}
		}
		String bookName = new String(request.getParameter("bookname").getBytes("iso-8859-1"),"UTF-8");
		List rsList = null;
		try{
			List<BookBean> list = new <BookBean>ArrayList();
			ResultSetToList rstl = new ResultSetToList();
			ResultSet rs = bookDao.managerBookList(con, null, bookName, grade, bookObject);
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
