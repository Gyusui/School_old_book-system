package com.SOB.action.book;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.SOB.dao.book.OrderDao;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.ResponseUtil;

public class DeleteOrderAction {
public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		Connection con = DataBaseConnManager.getConnection_2();
		String orderid = request.getParameter("orderid");
		OrderDao od = new OrderDao();
		JSONObject jb = new JSONObject();
		try{
			int bl = od.deleteOrder(con, orderid);
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
