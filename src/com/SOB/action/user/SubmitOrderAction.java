package com.SOB.action.user;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SOB.bean.BookBean;
import com.SOB.dao.book.OrderDao;
import com.SOB.util.Cart;
import com.SOB.util.DataBaseConnManager;

public class SubmitOrderAction {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		Connection con = DataBaseConnManager.getConnection_2();
		
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("user_id");
		String receiptAddress = new String(request.getParameter("receiptAddress").getBytes("iso-8859-1"),"UTF-8");
		String phone = request.getParameter("phone");
		OrderDao orderDao = new OrderDao();
    	Cart cart = (Cart)session.getAttribute("myCart");
    	Double price = 0.00;
		if(cart != null){
		List<BookBean> products = cart.getAllProductFromCart();
			Iterator<BookBean> it = products.iterator();
			while(it.hasNext()){
				BookBean bb = it.next();
				price = price + new Double(bb.getPrice());
			}
		}
		String orderid = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+userId+"/Daijin/isSB";
		int num = orderDao.insertNewOrder(con, orderid, userId, price, phone, receiptAddress);
		request.setAttribute("orderSuccess", "OK");
		RequestDispatcher rd = request.getRequestDispatcher("resource/jsp/UserCart.jsp");
		rd.forward(request, response);
		
	}

}
