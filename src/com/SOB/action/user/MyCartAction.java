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
import com.SOB.dao.book.BooksDaoIface;
import com.SOB.dao.book.BooksDaoImpl;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.ResultSetToList;

public class MyCartAction {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String bookName = request.getParameter("bookName");
		Connection con = DataBaseConnManager.getConnection_2();
		BooksDaoIface bookDao = new BooksDaoImpl();
		String id = request.getParameter("bookId");
		List<BookBean> products_list;
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id") !=null){
			List rsList = null;
			RequestDispatcher rd = request.getRequestDispatcher("resource/jsp/UserCart.jsp");
			rd.forward(request, response);
		}else{
			try{
				List rsList = null;
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
				String msg = "ÇëÄúÏÈµÇÂ½£¡";
				request.setAttribute("noLogin", msg);
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

}
