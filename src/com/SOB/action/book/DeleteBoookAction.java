package com.SOB.action.book;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.SOB.dao.book.BooksDaoIface;
import com.SOB.dao.book.BooksDaoImpl;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.ResponseUtil;

public class DeleteBoookAction {
public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		Connection con = DataBaseConnManager.getConnection_2();
		String bookName = request.getParameter("bookname");
		BooksDaoIface bookDao = new BooksDaoImpl();
		JSONObject jb = new JSONObject();
		try{
			int bl = bookDao.deleteProInfor(con, bookName);
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
